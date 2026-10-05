package com.daw2;

/**
 * Represents a puzzle with a solution, points and attempts
 */
public class Puzzle {

    // Contador estático para asignar IDs únicos

    private static int contadorId = 0;

    // Atributos de instancia

    private final int id;  // final porque no cambia después de crearse

    private String nombre;

    private String descripcion;

    private String solucion;

    private int puntos;

    private String pista;

    private boolean resuelto;

    private int intentos;

    /**
     * Creates a new puzzle
     * @param nombre the name of the puzzle
     * @param descripcion the description of the puzzle
     * @param solucion the solution of the puzzle
     * @param puntos the points given for solving the puzzle
     * @param pista a hint for the puzzle
     */
    public Puzzle(String nombre, String descripcion, String solucion,
                  int puntos, String pista) {
        
        this.id = ++contadorId;

        this.nombre = nombre;

        this.descripcion = descripcion;

        this.solucion = solucion.toLowerCase().trim(); // Normalizamos la solución

        this.puntos = puntos;

        this.pista = pista;

        this.resuelto = false;

        this.intentos = 0;

    }

    /**
     * Creates a new puzzle without a hint
     * @param nombre the name of the puzzle
     * @param descripcion the description of the puzzle
     * @param solucion the solution of the puzzle
     * @param puntos the points given for solving the puzzle
     */
    public Puzzle(String nombre, String descripcion, String solucion, int puntos) {

        this(nombre, descripcion, solucion, puntos, "");

    }

    // ====== GETTERS ======
    /**
     * Gets the puzzle ID
     * @return the puzzle ID
     */
    public int getId() {

        return id;

    }

    /**
     * Gets the name of the puzzle
     * @return the name of the puzzle
     */
    public String getNombre() {

        return nombre;

    }

    /**
     * Gets the description of the puzzle
     * @return the puzzle description
     */
    public String getDescripcion() {

        return descripcion;

    }

    /**
     * Gets the points of the puzzle
     * @return the puzzle points
     */
    public int getPuntos() {

        return puntos;

    }

    /**
     * Checks if the puzzle has been solved
     * @return true if the puzzle is solved, false if is not
     */
    public boolean isResuelto() {

        return resuelto;

    }

    /**
     * Gets the number of attempts
     * @return the number of attempts
     */
    public int getIntentos() {

        return intentos;

    }

    /**
     * Gets a hint of the puzzle
     * @return a puzzle hint
     */
    public String getPista() {

        return pista;

    }

    // ====== SETTERS ======
    /**
     * Changes the name of the puzzle
     * @param nombre the new name of the puzzle
     */
    public void setNombre(String nombre) {

        this.nombre = nombre;

    }

    /**
     * Changes the description of the puzzle
     * @param descripcion the new description of the puzzle
     */
    public void setDescripcion(String descripcion) {

        this.descripcion = descripcion;

    }

    // ====== MÉTODOS DE LÓGICA DE NEGOCIO ======
    /**
     * Tries to solve the puzzle with a given answer
     * @param respuesta the answer given by the player
     * @return true if the answer is correct, false if is not
     */
    public boolean intentarResolver(String respuesta) {

        this.intentos++;

        String respuestaNormalizada = respuesta.toLowerCase().trim();

        if (respuestaNormalizada.equals(this.solucion)) {

            this.resuelto = true;

            System.out.println("Correcto. Puzzle '" + nombre + "' resuelto");

            System.out.println("   Ganaste " + puntos + " puntos en " +
                             intentos + " intentos");

            return true;

        } else {

            System.out.println("Respuesta incorrecta. Intento #" + intentos);

            // Mostrar pista después de 3 intentos

            if (intentos >= 3 && !pista.isEmpty()) {

                System.out.println("Pista: " + pista);

            }

            return false;

        }

    }

    /**
     * Resets the puzzle and the number of attempts
     */
    public void reiniciar() {

        this.resuelto = false;

        this.intentos = 0;

    }

    /**
     * Creates a copy of the puzzle
     * @return a new puzzle with the same information
     */
    public Puzzle clonar() {

        return new Puzzle(nombre, descripcion, solucion, puntos, pista);

    }

    /**
     * Returns a text with the puzzle information
     * @return the puzzle ID, name, state and points
     */
    @Override
    public String toString() {

        String estado = resuelto ? "Resuelto" : "Pendiente";

        return String.format("Puzzle #%d: %s [%s] - %d pts",

                           id, nombre, estado, puntos);

    }

    /**
     * Checks if two puzzles have the same ID
     * @param obj the object to compare
     * @return true if both puzzles have the same ID, false if is not
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        Puzzle puzzle = (Puzzle) obj;

        return id == puzzle.id;

    }

}
