package View;

import javax.swing.*;

public class OpcaoView {

    public static Opcao select() {

        Opcao ret = (Opcao) JOptionPane.showInputDialog(
                null,
                "selecione Uma Opção",
                "Menu",
                JOptionPane.QUESTION_MESSAGE,
                null,
                Opcao.values(),
                Opcao.CADASTRAR_PRODUTO);

        return ret != null ? ret : Opcao.ENCERRAR_SISTEMA;
    }

}
