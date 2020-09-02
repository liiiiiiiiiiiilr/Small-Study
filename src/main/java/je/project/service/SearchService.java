package je.project.service;

import java.util.List;

import je.project.domain.Cost;
import je.project.domain.Custom;
import je.project.domain.Hub;
import je.project.domain.Repair;

public interface SearchService {
    /** 
     * 查询所有客户信息 
    */
    public List<Custom> searchallCustom();//查询所有客户信息 

    public List<Repair> searchallRepair();//查询维修任务

    public List<Hub> searchallHub();//查询库存

    public List<Cost> seachallCost();//查询结算信息

    public List<Custom> searchCustombyName(String name);//查询客户信息 

    public List<Repair> searchRepairbyId(int id);//查询维修任务

    public List<Hub> searchHubByname(String name);//查询库存

    public List<Cost> seachCostbyId(int id);//查询结算信息



}