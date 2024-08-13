public class ForcaBruta implements Runnable {

    private long minimo, maximo;
    private String senhaGerada;

    public boolean verificarSenha(String senha){
        return senha.equalsIgnoreCase(senhaGerada);
    }

    public ForcaBruta(long minimo, long maximo, String senha){
        this.minimo = minimo;
        this.maximo = maximo;
        this.senhaGerada = senha;
    }   

    @Override
    public void run() {
        long j=0;
        for(long i=minimo; !App.semaforo.chaveDescoberta && i<=maximo; i++){
            if(verificarSenha(String.valueOf(i))){
                System.out.println("Senha encontrada: " + i);
                System.out.println(minimo);
                App.semaforo.ativar();
            }
            if(App.semaforo.chaveDescoberta){
                System.out.println("Thread " + minimo + " ciente " + i);
            }
            //System.out.println(i);
            j=i;
        }
        System.out.println("Thread " + minimo + " terminada " + j);
    }
    
}
