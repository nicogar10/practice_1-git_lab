package com.daw2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ScapeRoom extends Juego {

    private String tematica;
    private List<Puzzle> puzzles;
    private int tiempoRestante;
    private String nivelDificultad;

    public ScapeRoom(String nombre, String tematica, int maxJugadores,int duracionMinutos) {
        super(nombre, maxJugadores, duracionMinutos);
        this.tematica = tematica;
        this.puzzles = new ArrayList<>();
        this.tiempoRestante = duracionMinutos;
        this.nivelDificultad = "Media";
    }

    // Constructor con valores por defecto
    public ScapeRoom(String nombre, String tematica) {
        this(nombre, tematica, 6, 60);
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    // Encapsulación defensiva: se devuelve una copia para que quien reciba
    // la lista no pueda modificar la lista interna del objeto
    public List<Puzzle> getPuzzles() {
        return new ArrayList<>(puzzles);
    }

    public int getNumPuzzles() {
        return puzzles.size();
    }

    //Funcion de cuenta con filtro de funcion booleana
    public long getPuzzlesResueltos() {
        return puzzles.stream().filter(Puzzle::isResuelto)
                //equivalente .filter(p -> p.isResuelto())
                .count();

    }

    public String getNivelDificultad() {
        return nivelDificultad;
    }

    public void setNivelDificultad(String nivelDificultad) {
        this.nivelDificultad = nivelDificultad;
    }

    public void agregarPuzzle(Puzzle puzzle) {
        if (puzzle != null) {
            puzzles.add(puzzle);
            System.out.println("Puzzle '" + puzzle.getNombre() + "' agregado al escape room");
        } else {
            System.err.println("Error: No se puede agregar un puzzle nulo");
        }
    }

    public boolean eliminarPuzzle(int puzzleId) {
        boolean eliminado = puzzles.removeIf(p -> p.getId() == puzzleId);

        if (eliminado) {
            System.out.println("Puzzle con ID " + puzzleId + " eliminado");
        } else {
            System.out.println("No se encontró puzzle con ID " + puzzleId);
        }

        return eliminado;
    }

    public Puzzle obtenerPuzzlePorIndice(int indice) {
        try {
            return puzzles.get(indice);
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Error: No existe puzzle en la posición " + indice);
            return null;
        }
    }

    // Método auxiliar para mostrar todos los puzzles por consola
    public void listarPuzzles() {
        puzzles.forEach(System.out::println);
    }
    
    /**
     * Ejercicio 1.1: añade un método existePuzzle(int puzzleId)
     * que devuelva true si hay algún puzzle con ese id.
     * Utiliza anyMatch() sobre el stream de puzzles.
    */
    public boolean existePuzzle (int puzzleId) {
        return puzzles.stream().anyMatch(p -> p.getId() == puzzleId);
    }

    /**
     * Ejercicio 1.2: añade un método obtenerPuzzlesMenosValiosos(int n)
     * que devuelva los n puzzles de menor puntuación.
     */
    public List<Puzzle> obtenerPuzzlesMenosValiosos(int n) {
        return puzzles.stream().sorted((p1, p2) -> Integer.compare(p1.getPuntos(), p2.getPuntos())).limit(n).collect(Collectors.toList());
    }

    /**
     * Ejercicio 1.3: añade un método getPuzzlesPendientes() que devuelva
     * (como long) cuántos puzzles quedan sin resolver.
    */
    public long getPuzzlesPendientes() {
        return getNumPuzzles() - getPuzzlesResueltos();
    }

    /**
     * Ejercicio 1.4: añade un método hayPuzzlesPendientes() que devuelva true si queda al menos
     * un puzzle sin resolver y hayNPuzzlesPendientes(int n) que devuelva true si quedan n puzzles sin resolver. 
     */
    public boolean hayPuzzlesPendientes() {
        if (getPuzzlesPendientes() >= 1) {
            return true;
        } else return false;
    }

    public boolean hayNPuzzlesPendientes(int n) {
        if (getPuzzlesPendientes() == n) {
            return true;
        } else return false;
    }

}
