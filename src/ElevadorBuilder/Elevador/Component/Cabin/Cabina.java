package ElevadorBuilder.Elevador.Component.Cabin;

import ElevadorBuilder.Elevador.Component.Cabin.Move.MoveTypes.Subir;
import ElevadorBuilder.Elevador.Component.Cabin.Move.Mover;

public class Cabina {
    private DireccionElevador direccionPrevista;
    private DireccionElevador direccionActual;
    private int pisoActual;

    private Mover move;
    private BotonDetenerse botonDetenerse;
    private InterruptorEmergencia interruptorEmergencia;

    public Cabina(DireccionElevador direccionPrevista, DireccionElevador direccionActual, int pisoActual, Mover move, BotonDetenerse botonDetenerse, InterruptorEmergencia interruptorEmergencia) {
        this.direccionPrevista = direccionPrevista;
        this.direccionActual = direccionActual;
        this.pisoActual = pisoActual;
        this.move = move;
        this.botonDetenerse = botonDetenerse;
        this.interruptorEmergencia = interruptorEmergencia;
    }

    public Cabina() {
        this.direccionPrevista = DireccionElevador.NINGUNA;
        this.direccionActual = DireccionElevador.NINGUNA;
        this.pisoActual = 1;
        this.move = new Subir();
        this.botonDetenerse = new BotonDetenerse();
        this.interruptorEmergencia = new InterruptorEmergencia();
    }

    public DireccionElevador getDireccionPrevista() {
        return direccionPrevista;
    }

    public void setDireccionPrevista(DireccionElevador direccionPrevista) {
        this.direccionPrevista = direccionPrevista;
    }

    public DireccionElevador getDireccionActual() {
        return direccionActual;
    }

    public void setDireccionActual(DireccionElevador direccionActual) {
        this.direccionActual = direccionActual;
    }

    public int getPisoActual() {
        return pisoActual;
    }

    public void setPisoActual(int pisoActual) {
        this.pisoActual = pisoActual;
    }

    public Mover getMove() {
        return move;
    }

    public void setMove(Mover move) {
        this.move = move;
    }

    public BotonDetenerse getBotonDetenerse() {
        return botonDetenerse;
    }

    public void setBotonDetenerse(BotonDetenerse botonDetenerse) {
        this.botonDetenerse = botonDetenerse;
    }

    public InterruptorEmergencia getInterruptorEmergencia() {
        return interruptorEmergencia;
    }

    public void setInterruptorEmergencia(InterruptorEmergencia interruptorEmergencia) {
        this.interruptorEmergencia = interruptorEmergencia;
    }
}
