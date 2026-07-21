package subsistema2.cep;

public class cepApi {

    private static cepApi instancia = new cepApi();

    private cepApi() {
        super();
    }

    public static cepApi getInstance() {
        return instancia;
    }

    public String recuperarCidade(String cep) {
        return "Sao Paulo";
    }

    public String recuperarEstado(String cep) {
        return "SP";
    }
}
