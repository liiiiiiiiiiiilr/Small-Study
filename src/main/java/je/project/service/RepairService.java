package je.project.service;

import java.util.List;

import je.project.domain.Repair;
import je.project.domain.Staff;

/**
 * 维修相关
 * @author 刘海鑫
 */
public interface RepairService {
    /**
     * 获取所有维修任务
     * @return 维修任务的列表
     */
    List<Repair> getAllTasks();

    /**
     * 获取未分配工程师的维修任务
     * @return 维修任务列表
     */
    List<Repair> getUnDelieveredTasks();

    /**
     * 获取长期未分配的维修任务
     * @param sec
     * @return
     */
    List<Repair> getUnDelieveredTasks(int sec);

    /**
     * 根据工程师的id查询维修任务
     * @param engineerid
     * @return 维修任务列表
     */
    List<Repair> getOnesTasks(Integer engineerid);

    /**
     * 进行维修工作
     * @param id 维修编号
     * @param scan 检测记录
     * @param time 检测时间
     * @param workload 工作量
     * @param parts 维修使用的器件
     * @param status 维修状态
     * @return 修改是否成功
     */
    Boolean doRepair(Integer id,String scan,String repair,String workload,String parts,Integer status);

    /**
     * 通过工程师的id获取其name
     * @param id id
     * @return 工程师姓名
     */
    String getEngineerName(Integer id);

    /**
     * 获取所有工程师
     */
    List<Staff> getAllEngineers();

    /**
     * 根据工程师id获取他的工作总数
     * @param id id
     * @return
     */
    Integer getWorkCount(Integer id);

    /**
     * 把某个任务分配给某个工程师
     * @param rid 任务的id
     * @param eid 工程师的id
     * @return 成功
     */
    Boolean delieverTo(Integer rid,Integer eid);

    /**
     * 获取某个工程师
     * @param id
     * @return
     */
    Staff getEngineerByid(Integer id);

    /**
     * 获取任务
     */
    Repair getRepairTask(Integer id);

    /**
     * 删除某个维修任务
     * @param id
     * @return
     */
    Boolean delRepairTask(Integer id);
}