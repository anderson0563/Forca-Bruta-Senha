public class Senha implements Runnable{

    private String senha;

    public Senha(){
        senha = "";
    }

    public String getSenha(){
        return this.senha;
    }

    public int senha(int max, int min){
        int random_int = (int)Math.floor(Math.random()*(max-min)+min);
        return random_int;
    }

    @Override
    public void run() {
        for(int i=0; i<9; i++)
            senha += senha(1, 10);
    }
    
}
