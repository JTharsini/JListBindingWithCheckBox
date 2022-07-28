import java.util.ArrayList;
import java.util.List;

public class MyPanelModel extends AbstractModelObject {
	List<RecipientWrapper> list;
	public List<RecipientWrapper> getGroups() {
		return list;
	}

	public void setGroups(List<RecipientWrapper> list) {
		this.list = new ArrayList<>(list);
		firePropertyChange("groups", null, this.list);
	}
}
