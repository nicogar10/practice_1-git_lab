package com.daw2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents an escape room with puzzles and a difficulty level
 */
public class ScapeRoom extends Juego {

    private String tematica;
    private List<Puzzle> puzzles;
    private int tiempoRestante;
    private String nivelDificultad;

    /**
     * Creates a new escape room
     * @param nombre the name of the escape room
     * @param tematica the theme of the escape room
     * @param maxJugadores the maximum number of players
     * @param duracionMinutos the duration in minutes
     */
    public ScapeRoom(String nombre, String tematica, int maxJugadores, int duracionMinutos) {
        super(nombre, maxJugadores, duracionMinutos);
        this.tematica = tematica;
        this.puzzles = new ArrayList<>();
        this.tiempoRestante = duracionMinutos;
        this.nivelDificultad = "Media";
    }

    /**
     * Creates a new escape room with default values
     * @param nombre the name of the escape room
     * @param tematica the theme of the escape room
     */
    public ScapeRoom(String nombre, String tematica) {
        this(nombre, tematica, 6, 60);
    }

    /**
     * Gets the theme of the escape room
     * @return the escape room theme
     */
    public String getTematica() {
        return tematica;
    }

    /**
     * Changes the theme of the escape room
     * @param tematica the new theme of the escape room
     */
    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    // Encapsulación defensiva: se devuelve una copia para que quien reciba
    // la lista no pueda modificar la lista interna del objeto

    /**
     * Gets the list of puzzles
     * @return a copy of the puzzle list
     */
    public List<Puzzle> getPuzzles() {
        return new ArrayList<>(puzzles);
    }

    /**
     * Gets the number of puzzles
     * @return the number of puzzles
     */
    public int getNumPuzzles() {
        return puzzles.size();
    }

    // Funcion de cuenta con filtro de funcion booleana

    /**
     * Counts the solved puzzles
     * @return the number of solved puzzles
     */
    public long getPuzzlesResueltos() {
        return puzzles.stream().filter(Puzzle::isResuelto)
                // equivalente .filter(p -> p.isResuelto())
                .count();
    }

    /**
     * Gets the difficulty level
     * @return the difficulty level
     */
    public String getNivelDificultad() {
        return nivelDificultad;
    }

    /**
     * Changes the difficulty level
     * @param nivelDificultad the new difficulty level
     */
    public void setNivelDificultad(String nivelDificultad) {
        this.nivelDificultad = nivelDificultad;
    }

    /**
     * Adds a puzzle to the escape room
     * @param puzzle the puzzle to add
     */
    public void agregarPuzzle(Puzzle puzzle) {
        if (puzzle != null) {
            puzzles.add(puzzle);
            System.out.println("Puzzle '" + puzzle.getNombre() + "' agregado al escape room");
        } else {
            System.err.println("Error: No se puede agregar un puzzle nulo");
        }
    }

    /**
     * Removes a puzzle using its ID
     * @param puzzleId the ID of the puzzle to remove
     * @return true if the puzzle was removed, false if is not
     */
    public boolean eliminarPuzzle(int puzzleId) {
        boolean eliminado = puzzles.removeIf(p -> p.getId() == puzzleId);

        if (eliminado) {
            System.out.println("Puzzle con ID " + puzzleId + " eliminado");
        } else {
            System.out.println("No se encontró puzzle con ID " + puzzleId);
        }

        return eliminado;
    }

    /**
     * Gets a puzzle using the position in the list
     * @param indice the position of the puzzle
     * @return the puzzle at the given position, or null if is not found
     */
    public Puzzle obtenerPuzzlePorIndice(int indice) {
        try {
            return puzzles.get(indice);
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Error: No existe puzzle en la posición " + indice);
            return null;
        }
    }

    // Método auxiliar para mostrar todos los puzzles por consola

    /**
     * Shows all puzzles
     */
    public void listarPuzzles() {
        puzzles.forEach(System.out::println);
    }

    /**
     * Checks if a puzzle with the given ID exists
     * @param puzzleId the ID of the puzzle to search
     * @return true if the puzzle exists, false if is not
     */
    public boolean existePuzzle(int puzzleId) {
        return puzzles.stream().anyMatch(p -> p.getId() == puzzleId);
    }

    /**
     * Gets the puzzles with the lowest points
     * @param n the maximum number of puzzles to return
     * @return a list with the puzzles with the lowest points
     */
    public List<Puzzle> obtenerPuzzlesMenosValiosos(int n) {
        return puzzles.stream()
                .sorted((p1, p2) -> Integer.compare(p1.getPuntos(), p2.getPuntos()))
                .limit(n)
                .collect(Collectors.toList());
    }

    /**
     * Counts the puzzles that are not solved
     * @return the number of pending puzzles
     */
    public long getPuzzlesPendientes() {
        return getNumPuzzles() - getPuzzlesResueltos();
    }

    /**
     * Checks if there are pending puzzles
     * @return true if there is at least one pending puzzle, false if is not
     */
    public boolean hayPuzzlesPendientes() {
        if (getPuzzlesPendientes() >= 1) {
            return true;
        } else return false;
    }

    /**
     * Checks if there are a specific number of pending puzzles
     * @param n the number of pending puzzles to check
     * @return true if there are exactly n pending puzzles, false if is not
     */
    public boolean hayNPuzzlesPendientes(int n) {
        if (getPuzzlesPendientes() == n) {
            return true;
        } else return false;
    }

}
