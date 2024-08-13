public class Semaforo
{
    protected boolean chaveDescoberta;
    public Semaforo ()
    {
        this.chaveDescoberta = false;
    }
    public Semaforo (boolean valorPadrao)
    {
        this.chaveDescoberta = valorPadrao;
    }
    public synchronized void desativar ()
    {
        this.chaveDescoberta = false;
    }
    public synchronized void ativar ()
    {
        this.chaveDescoberta = true;
        notify();
        System.out.println("Semaforo ativado");
    }
}