package helper;

import bean.PieceJointe;
import java.util.List;

import javafx.scene.control.TableView;

public class PieceJointeFxHelper extends AbstractFxHelper<PieceJointe> {

    private static AbstractFxHelperItem[] titres;

    static {

        titres = new AbstractFxHelperItem[]{
            new AbstractFxHelperItem("FILIERE", "filiere"),
            new AbstractFxHelperItem("PIECE JOINTE", "nom"),
            new AbstractFxHelperItem("NOMBRE A FOURNIR", "nombre")
            
        };
    }

    public PieceJointeFxHelper(TableView<PieceJointe> table, List<PieceJointe> list) {
        super(titres, table, list);
    }

    public PieceJointeFxHelper(TableView<PieceJointe> table) {
        super(titres, table);
    }

}
