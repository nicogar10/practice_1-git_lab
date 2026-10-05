package com.daw2;

/**
 * Represents a game with players, duration and score
 * Juego
 */
public class Juego {

    // Atributos privados (encapsulación)

    private String nombre;

    private int maxJugadores;

    private int duracionMinutos;

    protected int puntuacion;  // protected para que las clases hijas puedan acceder

    private boolean estaActivo;

    /**
     * Creates a new game
     * @param nombre the name of the game
     * @param maxJugadores the maximum number of players
     * @param duracionMinutos the game duration in minutes
     */
    public Juego(String nombre, int maxJugadores, int duracionMinutos) {

        this.nombre = nombre;

        this.maxJugadores = maxJugadores;

        this.duracionMinutos = duracionMinutos;

        this.puntuacion = 0;

        this.estaActivo = false;

    }

    // Constructor sobrecargado con valores por defecto
    /**
     * Creates a new game with default values
     * @param nombre the name of the game
     */
    public Juego(String nombre) {

        this(nombre, 1, 60); // Llama al constructor principal

    }

    // ====== GETTERS ======
    /**
     * Gets the name of the game
     * @return the name of the game
     */
    public String getNombre() {

        return nombre;

    }

    /**
     * Gets the maximum number of players
     * @return the maximum number of players
     */
    public int getMaxJugadores() {

        return maxJugadores;

    }

    /**
     * Gets the game duration in minutes
     * @return the game duration in minutes
     */
    public int getDuracionMinutos() {

        return duracionMinutos;

    }

    /**
     * Gets the current score
     * @return the current score
     */
    public int getPuntuacion() {

        return puntuacion;

    }

    /**
     * Checks if the game is active
     * @return true if the game is active, false if is not
     */
    public boolean isEstaActivo() {

        return estaActivo;

    }

    // ====== SETTERS ======
    /**
     * Changes the current score
     * @param puntuacion the new score
     */
    public void setPuntuacion(int puntuacion) {

        if (puntuacion >= 0) {

            this.puntuacion = puntuacion;

        } else {

            System.err.println("Error: La puntuación no puede ser negativa");

        }

    }

    /**
     * Changes the maximum number of players
     * @param maxJugadores the new maximum number of players
     */
    public void setMaxJugadores(int maxJugadores) {

        if (maxJugadores > 0) {

            this.maxJugadores = maxJugadores;

        } else {

            System.err.println("Error: Debe haber al menos 1 jugador");

        }

    }

    // ====== MÉTODOS DE LA CLASE ======
    /**
     * Starts the game and resets the score
     */
    public void iniciarJuego() {

        this.estaActivo = true;

        this.puntuacion = 0;

        System.out.println("Juego '" + nombre + "' iniciado.");

    }

    /**
     * Finishes the game and shows the final score
     */
    public void finalizarJuego() {

        this.estaActivo = false;

        System.out.println("Juego finalizado. Puntuación final: " + puntuacion);

    }

    /**
     * Adds points to the current score
     * @param puntos the points to add
     */
    public void agregarPuntos(int puntos) {

        if (puntos > 0) {

            this.puntuacion += puntos;

            System.out.println("+" + puntos + " puntos. Total: " + this.puntuacion);

        } else {

            System.out.println("Los puntos deben ser positivos");

        }

    }

    @Override
    /**
     * Returns a text with the game information
     * @return the name, number of players and current state of the game
     */
    public String toString() {

        String estado = estaActivo ? "Activo" : "Inactivo";

        return String.format("Juego: %s | Jugadores: %d | Estado: %s",

                           nombre, maxJugadores, estado);

    }

}