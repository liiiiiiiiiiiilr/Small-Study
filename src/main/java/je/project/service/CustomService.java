package je.project.service;

import java.util.List;

import je.project.domain.Custom;

public interface CustomService {

    /**
     * 获取所有客户信息
     */
    List<Custom> search(String idCard);
    
    /**
     * 插入客户信息
     * @param idCard 身份证号
     * @param type 客户性质1家庭2单位3代理商4签约
     * @param company 单位名称
     * @param fTel 座机号码
     * @param tel 用户手机号
     * @param address 用户的地址
     * @param postcode 邮编
     * @param name 联系人
     * @param email email
     * @return 操作是否成功
     */
    Boolean insertCustom(String idCard,Integer type,String company,String fTel,String tel,String address,
    String postcode,String name,String email);

    /**
     * 通过id获取客户信息
     * @param id id
     */
    Custom getCustomById(Integer id);

    /**
     * 更新用户信息
     * @param id 要修改的客户id
     * @param idCard 身份证号
     * @param type 客户性质1家庭2单位3代理商4签约
     * @param company 单位名称
     * @param fTel 座机号码
     * @param tel 用户手机号
     * @param address 用户的地址
     * @param postcode 邮编
     * @param name 联系人
     * @param email email
     * @return 操作是否成功
     */
    Boolean updateCustom(Integer id,String idCard,Integer type,String company,String fTel,String tel,String address,
    String postcode,String name,String email);

    Boolean deleteCustom(Integer id);
}