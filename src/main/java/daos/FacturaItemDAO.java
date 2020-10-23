package daos;

import model.*;

public interface FacturaItemDAO {

    public void insert(FacturaItem facturaItem);
    public void update(FacturaItem facturaItem);
    public void delete(Integer id);
    public FacturaItem queryId(Integer id);
}
