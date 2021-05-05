package ModeloEmpleado;

public class Cronometro implements Runnable{
    private int hora, minutos, segundos;
	private static Cronometro cronometro = null;
	private boolean isAndando;
    
    private Cronometro() {
    	this.hora = 0;
    	this.minutos = 0;
    	this.segundos = 0;
    }
    
	@Override
	public void run() {
	    try {
	    	while(this.isAndando) {
				Thread.sleep(1000);
				this.segundos += 1;
				if(this.segundos == 59) {
					this.minutos += 1;
					this.segundos = 0;
					if(this.minutos == 59) {
						this.hora += 1;
						this.minutos = 0;
					}
				}
	    	}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	} 
	
	public static Cronometro getInstance() {
		if(Cronometro.cronometro == null)
			Cronometro.cronometro = new Cronometro();
		return Cronometro.cronometro;
	}

	public synchronized boolean isAndando() {
		return isAndando;
	}

	public synchronized void setAndando(boolean isAndando) {
		this.isAndando = isAndando;
	}

	public int getHora() {
		return hora;
	}

	public void setHora(int hora) {
		this.hora = hora;
	}

	public int getMinutos() {
		return minutos;
	}

	public void setMinutos(int minutos) {
		this.minutos = minutos;
	}

	public int getSegundos() {
		return segundos;
	}

	public void setSegundos(int segundos) {
		this.segundos = segundos;
	}
}
