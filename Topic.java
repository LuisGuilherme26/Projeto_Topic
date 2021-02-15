import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Pass{
    public String name;
    public int idade;

    public Pass(String name, int idade) {
        this.name = name;
        this.idade = idade;
    }
    
    public String toString(){
        return "Nome:"+name+", Idade:"+idade;
    }
}

public class Topic{
    public ArrayList<Pass> cadeiras;
    public int qtdPref;
    
    public Topic(int capacidade ,int qtdPref){
        Random r = new Random();
        int rad;
        this.qtdPref = qtdPref;
        cadeiras = new ArrayList<Pass>();
        for (int i = 0; i < capacidade; i++) {
            cadeiras.add(new Pass(null, 0));
        }
        for (int i = 0; i < this.qtdPref; i++) {
            rad = r.nextInt(capacidade);
            cadeiras.set(rad, new Pass("@ ", 0));
        }
    }
    
    public int procurarPessoa(String nome){
        for (int i = 0; i < cadeiras.size(); i++) {
            if(cadeiras.get(i).name.equals(nome)){
                return i;
            }
        }
        return -1;
    }
    
    public int procurarVaga(int posIni, int posFim){
        for (int i = posIni; i <= posFim; i++) {
            if(cadeiras.get(i).name == null){
                return i;
            }
        }
        return -1;    
    }
    
   public int vagaPref(int posIni, int posFim){
       for (int i = posIni; i <= posFim; i++) {
           if("@ ".equals(cadeiras.get(i).name)){
               return i;
           }
       }
       return -1;
   }
    
    public boolean subir(Pass pass){
        int vaga = 0;
        if(pass.idade > 50){
            vaga = vagaPref(1, cadeiras.size());
            cadeiras.set(vaga, new Pass("@" + pass.name, pass.idade));
        }else{
            vaga = procurarVaga(1, cadeiras.size());
            cadeiras.set(vaga, pass);
        }
        return false;
    }

    public Pass descer(String name){
        int vaga;
        vaga = procurarPessoa(name);
        cadeiras.set(vaga, new Pass(null, 0));
        return null;
    }
    
    public String toString(){
        return "Cadeiras:"+cadeiras;
    }
    
    public static void main(String[] args) {
        Topic topic = new Topic(7,2);
        while(true){
            Scanner tcl = new Scanner(System.in);
            String comando = tcl.nextLine();
            if(comando.equals("Subir")){
                System.out.println("Nome e idade do passageiro");
                Scanner nome = new Scanner(System.in); 
                Scanner idade = new Scanner(System.in); 
                topic.subir(new Pass(nome.nextLine(), idade.nextInt()));
            }else if(comando.equals("Descer")){
                System.out.println("Quem vai descer?");
                Scanner tirar = new Scanner(System.in);
                topic.descer(tirar.nextLine());
            }else if(comando.equals("Mostrar")){
                System.out.println(topic);
            }else if(comando.equals("Parar")){
                break;
            }
        }
    }
}

