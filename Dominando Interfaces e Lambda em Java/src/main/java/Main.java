import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import static Keyword.Client.getName;
import static Keyword.Client.setName;

public class Main {
    public static void main(String[] args) {
        // var musicPlayer = new MusicPlayer() {
        //
        //     //Classes anonimas
        //     @Override
        //     public void playMusic() {
        //         System.out.println("Tocando a música");
        //     }
        //
        //     @Override
        //     public void pauseMusic() {
        //
        //     }
        //
        //     @Override
        //     public void stopMusic() {
        //
        //     }
        // };
        //
        // musicPlayer.playMusic();

        //Ver o nome da classe
        // System.out.println(musicPlayer.getClass());

        Computer musicPlayer = new Computer();
        //O tipo será computer
        runMusic(musicPlayer);
        // runMusic(new Computer());
        runVideo(musicPlayer);
        // runVideo(new Computer());

        //Lista de User
        List<User> users =
                //Popular. Vai retornar uma coleção imutável de itens
                List.of(
                        new User("Maria", 21),
                        new User("João", 32),
                        new User("Eduardo", 40),
                        new User("Ana", 19));

        //User::nome = chama o nome de users
        System.out.println("\nNome:");
        printStringValue(User::nome, users);

        //Retorna a idade. Primeiro precisa converter para string
        System.out.println("\nIdade:");
        printStringValue(user -> String.valueOf(user.age()), users);

        //Retorna o objeto inteiro
        System.out.println("\nObjeto inteiro:");
        printStringValue(Record::toString, users);

        System.out.println("\nObjeto inteiro:");
        users.forEach(System.out::println);


        //Importação Estatica
        setName("teste");
        System.out.println(getName());

    }

    //Function = Recebe 1 argumento de um tipo e retorna outro tipo
    private static void printStringValue(Function<User, String> callback, List<User> users){
        users.forEach(u -> System.out.println(callback.apply(u)));
    }

    public static void runVideo(VideoPlayer videoPlayer){
        videoPlayer.playVideo();
    }

    public static void runMusic(MusicPlayer musicPlayer){
        musicPlayer.playMusic();
    }
}
