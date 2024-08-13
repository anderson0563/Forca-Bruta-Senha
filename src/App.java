import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class App {

    static Semaforo semaforo = new Semaforo();

    public static void main(String[] args) throws Exception {
        System.out.println("Quebrando senha por força bruta");
        Senha threadSenha = new Senha();
        Thread gerador = new Thread(threadSenha);
        gerador.start();
        while(gerador.isAlive());
        System.out.println(threadSenha.getSenha());

       System.out.println("Procurando a senha");
        ForcaBruta threadForcaBruta = new ForcaBruta(1, 999999999, threadSenha.getSenha());
        Thread crack = new Thread(threadForcaBruta);
        ThreadMXBean thMxB = ManagementFactory.getThreadMXBean();
        long t0 = thMxB.getCurrentThreadCpuTime();
        crack.start();
        while(crack.isAlive());
        long t1 = thMxB.getCurrentThreadCpuTime();
        System.out.println("Tempo de execução: " + (t1 - t0) / 1000000000. + "s");
        System.out.println("Procura terminada.");


        System.out.println("Procurando a senha - Paralelo");
        long valorMaximo = 999999999;
        ForcaBruta threadForcaBrutaP = new ForcaBruta(1, Math.round(valorMaximo/2), threadSenha.getSenha());
        Thread crackP = new Thread(threadForcaBrutaP);
        ForcaBruta threadForcaBrutaP2 = new ForcaBruta(Math.round(valorMaximo/2), valorMaximo, threadSenha.getSenha());
        Thread crackP2 = new Thread(threadForcaBrutaP2);
        thMxB = ManagementFactory.getThreadMXBean();
        t0 = thMxB.getCurrentThreadCpuTime();
        crackP.start();
        crackP2.start();
        while(crackP.isAlive() && crackP2.isAlive());
        t1 = thMxB.getCurrentThreadCpuTime();
        System.out.println("Tempo de execução: " + (t1 - t0) / 1000000000. + "s");
        System.out.println("Procura terminada.");

    }
}
