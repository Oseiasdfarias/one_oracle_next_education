
import br.com.projetoConversor.menu.Menu;

public class Principal {
    public static void main(String[] args) {
        Menu meuMenu = new Menu();
        while (true) {
            meuMenu.menu();
            meuMenu.selecinarConversao();
        }
    }
}
