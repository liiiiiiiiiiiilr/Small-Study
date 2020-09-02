package je.project.service;

import java.util.List;

import je.project.domain.Cost;
import je.project.domain.Custom;
import je.project.domain.Detail;
import je.project.domain.Repair;

public interface CostService {
    void writeCost(Cost cost);
    Cost getCostByID(int id);
    void deleteCostByID(int id);
    List<Cost> getAllCost();
    void deleteAllCost();
    List<Repair> getRepairByStatus();
    List<Detail> getDetailByRid(int id);
    void updateCost(Cost cost);
    Custom getCustomByUid(int uid);
//    void updateCost(Integer id,
//             BigDecimal cost,
//             BigDecimal materialcost,
//             String promise,
//             String note
//                    );
}
