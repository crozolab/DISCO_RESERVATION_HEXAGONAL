package com.ceiba.dominio.excepcion;

public class ExcepcionCredencialesIncorrectas extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ExcepcionCredencialesIncorrectas(String mensaje) {
        super(mensaje);
    }
}
