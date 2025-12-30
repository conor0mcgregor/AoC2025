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
        double[] coeficientesFuncionObjetivo = new double[numVariables];
        for (int i = 0; i < numVariables; i++) {
            coeficientesFuncionObjetivo[i] = 1.0;
        }
        LinearObjectiveFunction funcionObjetivo = new LinearObjectiveFunction(coeficientesFuncionObjetivo, 0);

        // Restricciones: Ax = independentTerms
        Collection<LinearConstraint> restricciones = new ArrayList<>();

        for (int i = 0; i < numRestricciones; i++) {
            double[] coeficientes = new double[numVariables];
            for (int j = 0; j < numVariables; j++) {
                coeficientes[j] = matriz[i][j];
            }

            // Para igualdad Ax = independentTerms, añadimos dos restricciones: Ax >= independentTerms y Ax <= independentTerms
            restricciones.add(new LinearConstraint(coeficientes, Relationship.GEQ, independentTerms[i]));
            restricciones.add(new LinearConstraint(coeficientes, Relationship.LEQ, independentTerms[i]));
        }

        // Restricción de no negatividad: xi >= 0
        for (int i = 0; i < numVariables; i++) {
            double[] coeficientes = new double[numVariables];
            coeficientes[i] = 1.0;
            restricciones.add(new LinearConstraint(coeficientes, Relationship.GEQ, 0));
        }

        // Resolver el problema de optimización
        SimplexSolver solver = new SimplexSolver();
        PointValuePair solucion = solver.optimize(
                new MaxIter(1000),
                funcionObjetivo,
                new LinearConstraintSet(restricciones),
                GoalType.MINIMIZE,
                new NonNegativeConstraint(true)
        );

        // Convertir double[] a int[]
        double[] solucionDouble = solucion.getPoint();
        int[] solucionInt = new int[numVariables];
        for (int i = 0; i < numVariables; i++) {
            solucionInt[i] = (int) Math.round(solucionDouble[i]);
        }

        return solucionInt;
    }

    private static int sumVector(int[] vector) {
        return Arrays.stream(vector).sum();
    }




}
