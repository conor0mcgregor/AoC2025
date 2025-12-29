package software.aoc.day9.b;

import java.util.*;

import static java.lang.Math.abs;

public class Board {
    private final List<Point> redTiles;
    private final Set<Point> greenTiles;

    public Board(List<Point> points) {
        this.redTiles = new ArrayList<>(points);
        this.greenTiles = calculateGreenTiles();
    }

    public long getBiggerRectangle() {
        long maxArea = 0;

        for (int i = 0; i < redTiles.size(); i++) {
            for (int j = i + 1; j < redTiles.size(); j++) {
                Point point1 = redTiles.get(i);
                Point point2 = redTiles.get(j);

                if (isValidRectangle(point1, point2)) {
                    long area = getArea(point1, point2);
                    maxArea = Math.max(area, maxArea);
                }
            }
        }

        return maxArea;
    }

    private boolean isValidRectangle(Point p1, Point p2) {
        int minX = Math.min(p1.x(), p2.x());
        int maxX = Math.max(p1.x(), p2.x());
        int minY = Math.min(p1.y(), p2.y());
        int maxY = Math.max(p1.y(), p2.y());

        // Verificar cada punto del rectángulo
        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                Point current = new Point(x, y);

                // Debe ser roja o verde
                if (!isRedTile(current) && !isGreenTile(current)) {
                    return false;
                }
            }
        }

        return true;
    }


    private Set<Point> calculateGreenTiles() {
        Set<Point> green = new HashSet<>();

        for (int i = 0; i < redTiles.size(); i++) {
            Point current = redTiles.get(i);
            Point next = redTiles.get((i + 1) % redTiles.size());

            green.addAll(getLineBetween(current, next));
        }

        // 2. Agregar fichas dentro del polígono
        green.addAll(getPointsInsidePolygon());

        return green;
    }

    private List<Point> getLineBetween(Point p1, Point p2) {
        List<Point> line = new ArrayList<>();

        if (p1.x() == p2.x()) {
            // Línea vertical
            int minY = Math.min(p1.y(), p2.y());
            int maxY = Math.max(p1.y(), p2.y());

            for (int y = minY; y <= maxY; y++) {
                line.add(new Point(p1.x(), y));
            }
        } else if (p1.y() == p2.y()) {
            // Línea horizontal
            int minX = Math.min(p1.x(), p2.x());
            int maxX = Math.max(p1.x(), p2.x());

            for (int x = minX; x <= maxX; x++) {
                line.add(new Point(x, p1.y()));
            }
        }

        return line;
    }


    private Set<Point> getPointsInsidePolygon() {
        Set<Point> inside = new HashSet<>();

        // Encontrar los límites del polígono
        int minX = redTiles.stream().mapToInt(Point::x).min().orElse(0);
        int maxX = redTiles.stream().mapToInt(Point::x).max().orElse(0);
        int minY = redTiles.stream().mapToInt(Point::y).min().orElse(0);
        int maxY = redTiles.stream().mapToInt(Point::y).max().orElse(0);

        // Verificar cada punto en el área delimitada
        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                Point point = new Point(x, y);

                if (isPointInsidePolygon(point)) {
                    inside.add(point);
                }
            }
        }

        return inside;
    }


    private boolean isPointInsidePolygon(Point point) {
        int intersections = 0;

        for (int i = 0; i < redTiles.size(); i++) {
            Point p1 = redTiles.get(i);
            Point p2 = redTiles.get((i + 1) % redTiles.size());

            if (rayIntersectsEdge(point, p1, p2)) {
                intersections++;
            }
        }

        return intersections % 2 == 1;
    }

    private boolean rayIntersectsEdge(Point point, Point edgeStart, Point edgeEnd) {
        // Asegurar que edgeStart.y <= edgeEnd.y
        if (edgeStart.y() > edgeEnd.y()) {
            Point temp = edgeStart;
            edgeStart = edgeEnd;
            edgeEnd = temp;
        }

        // El punto debe estar dentro del rango Y de la arista
        if (point.y() < edgeStart.y() || point.y() > edgeEnd.y()) {
            return false;
        }

        // Evitar contar vértices dos veces
        if (point.y() == edgeEnd.y()) {
            return false;
        }

        // Calcular la coordenada X de la intersección
        if (edgeStart.y() == edgeEnd.y()) {
            // Arista horizontal, no intersecta con rayo horizontal
            return false;
        }

        double xIntersection = edgeStart.x() +
                (double) (point.y() - edgeStart.y()) * (edgeEnd.x() - edgeStart.x()) /
                        (edgeEnd.y() - edgeStart.y());

        // La intersección debe estar a la derecha del punto
        return xIntersection >= point.x();
    }


    private long getArea(Point point1, Point point2) {
        return (long) (abs(point1.x() - point2.x()) + 1) *
                (abs(point1.y() - point2.y()) + 1);
    }


    private boolean isRedTile(Point point) {
        return redTiles.contains(point);
    }


    private boolean isGreenTile(Point point) {
        return greenTiles.contains(point);
    }

}