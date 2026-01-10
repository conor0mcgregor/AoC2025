package software.aoc.day10.b;
import org.apache.commons.math3.optim.MaxIter;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.*;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
public class LinearSystemsOptimizer {
    
    public static int minimizarPulsaciones(int[][] buttons, int[] stateTrage){
        return sumVector(resolverMinimizandoSuma(buttons, stateTrage));
    }

    private static int[] resolverMinimizandoSuma(int[][] matriz, int[] independentTerms) {
        int numVariables = matriz[0].length;
        int numRestricciones = matriz.length;

        // Función objetivo: minimizar x1 + x2 + x3 + ... + xn
        LinearObjectiveFunction funcionObjetivo = getLinearObjectiveFunction(numVariables);

        // Restricciones: Ax = independentTerms
        Collection<LinearConstraint> restricciones = new ArrayList<>();

        restrictIndependentTerms(matriz, independentTerms, numRestricciones, numVariables, restricciones);

        // Restricción de no negatividad: xi >= 0
        restrictNegative(numVariables, restricciones);

        // Resolver el problema de optimización
        PointValuePair solucion = getPointValuePair(funcionObjetivo, restricciones);

        // Convertir double[] a int[]
        return doublesToInts(solucion, numVariables);
    }

    private static LinearObjectiveFunction getLinearObjectiveFunction(int numVariables) {
        double[] coeficientesFuncionObjetivo = new double[numVariables];
        for (int i = 0; i < numVariables; i++) {
            coeficientesFuncionObjetivo[i] = 1.0;
        }
        LinearObjectiveFunction funcionObjetivo = new LinearObjectiveFunction(coeficientesFuncionObjetivo, 0);
        return funcionObjetivo;
    }

    private static void restrictIndependentTerms(int[][] matriz, int[] independentTerms, int numRestricciones, int numVariables, Collection<LinearConstraint> restricciones) {
        for (int i = 0; i < numRestricciones; i++) {
            double[] coeficientes = new double[numVariables];
            for (int j = 0; j < numVariables; j++) {
                coeficientes[j] = matriz[i][j];
            }

            // Para igualdad Ax = independentTerms, añadimos dos restricciones: Ax >= independentTerms y Ax <= independentTerms
            restricciones.add(new LinearConstraint(coeficientes, Relationship.GEQ, independentTerms[i]));
            restricciones.add(new LinearConstraint(coeficientes, Relationship.LEQ, independentTerms[i]));
        }
    }

    private static void restrictNegative(int numVariables, Collection<LinearConstraint> restricciones) {
        for (int i = 0; i < numVariables; i++) {
            double[] coeficientes = new double[numVariables];
            coeficientes[i] = 1.0;
            restricciones.add(new LinearConstraint(coeficientes, Relationship.GEQ, 0));
        }
    }

    private static int[] doublesToInts(PointValuePair solucion, int numVariables) {
        double[] solucionDouble = solucion.getPoint();
        int[] solucionInt = new int[numVariables];
        for (int i = 0; i < numVariables; i++) {
            solucionInt[i] = (int) Math.round(solucionDouble[i]);
        }
        return solucionInt;
    }

    private static PointValuePair getPointValuePair(LinearObjectiveFunction funcionObjetivo, Collection<LinearConstraint> restricciones) {
        SimplexSolver solver = new SimplexSolver();
        PointValuePair solucion = solver.optimize(
                new MaxIter(1000),
                funcionObjetivo,
                new LinearConstraintSet(restricciones),
                GoalType.MINIMIZE,
                new NonNegativeConstraint(true)
        );
        return solucion;
    }

    private static int sumVector(int[] vector) {
        return Arrays.stream(vector).sum();
    }




}
