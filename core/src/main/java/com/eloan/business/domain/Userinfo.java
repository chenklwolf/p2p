package com.eloan.business.domain;
import lombok.Getter;
import lombok.Setter;

import org.apache.ibatis.type.Alias;
import org.springframework.util.StringUtils;

import com.eloan.base.domain.BaseDomain;
import com.eloan.base.domain.SystemDictionaryItem;
import com.eloan.business.util.BitStatesUtils;

/**
 * 网站会员相关详细信息（前台用户）
 *
 * @author Stef
 */
@Getter
@Setter
@Alias("UserInfo")
public class Userinfo extends BaseDomain {
    private static final long serialVersionUID = -2194938919842714855L;
    private int version;// 版本号
    private Long bitState = 0L; // 位状态
    private String realName; // 对应实名认证中的真实姓名
    private String idNumber; // 对应实名认证中的身份证号
    private String email; // 用户邮箱
    private String phoneNumber = ""; // 手机号
    private int authScore = 0;//用户当前认证分数
    private Long realauthId;   //用户有效的实名认证对象

    // ====================== 会员基本资料 ===================
    private SystemDictionaryItem incomeGrade; // 月收入
    private SystemDictionaryItem marriage; // 婚姻情况
    private SystemDictionaryItem kidCount; // 子女情况
    private SystemDictionaryItem educationBackground; // 学历
    private SystemDictionaryItem houseCondition; // 住房条件

    public static Userinfo empty(Long id) {
        Userinfo userinfo = new Userinfo();
        userinfo.setId(id);
        userinfo.setBitState(BitStatesUtils.OP_BASIC_INFO);
        return userinfo;
    }

    public void addState(Long state) {
        this.bitState = BitStatesUtils.addState(this.bitState, state);
    }

    public void removeState(Long state) {
        this.bitState = BitStatesUtils.removeState(this.bitState, state);
    }

    public boolean getIsBindPhone() {
        return BitStatesUtils.hasState(bitState, BitStatesUtils.OP_BIND_PHONE);
    }

    public boolean getIsBindEmail() {
        return BitStatesUtils.hasState(bitState, BitStatesUtils.OP_BIND_EMAIL);
    }

    public boolean getBaseInfo() {
        return BitStatesUtils.hasState(bitState, BitStatesUtils.OP_BASE_INFO);
    }

    //实名认证
    public boolean getRealAuth() {
        return BitStatesUtils.hasState(bitState, BitStatesUtils.OP_REAL_AUTH);
    }

    public boolean getVedioAuth() {
        return BitStatesUtils.hasState(bitState, BitStatesUtils.OP_VEDIO_AUTH);
    }
    //当前用户有一个借款还在借款流程当中
    public boolean getHasBidRequest(){
        return BitStatesUtils.hasState(bitState, BitStatesUtils.OP_HAS_BIDRQUEST);
    }


    /**
     * 返回用户是否绑定银行卡
     *
     * @return
     */
    public boolean getIsBindBank() {
        return BitStatesUtils.hasState(this.bitState,
                BitStatesUtils.OP_BIND_BANKINFO);
    }


    /**
     * 返回用户是否有一个提现申请在处理流程当中
     *
     * @return
     */
    public boolean getHasWithdrawProcess() {
        return BitStatesUtils.hasState(this.bitState,
                BitStatesUtils.OP_HAS_MONEYWITHDRAW_PROCESS);
    }


    /**
     * 获取用户真实名字的隐藏字符串，只显示姓氏
     *
     *            真实名字
     * @return
     */
    public String getAnonymousRealName() {
        if (StringUtils.hasLength(realName)) {
            int len = realName.length();
            String replace = "";
            replace += realName.charAt(0);
            for (int i = 1; i < len; i++) {
                replace += "*";
            }
            return replace;
        }
        return realName;
    }

    /**
     * 获取用户真实名字的隐藏字符串，只显示姓氏
     *
     * @param realName
     *            真实名字
     * @return
     */
    public static String getAnonymousRealName(String realName) {
        if (StringUtils.hasLength(realName)) {
            int len = realName.length();
            String replace = "";
            replace += realName.charAt(0);
            for (int i = 1; i < len; i++) {
                replace += "*";
            }
            return replace;
        }
        return realName;
    }

    /**
     * 获取用户身份号码的隐藏字符串
     *
     * @param idNumber
     * @return
     */
    public static String getAnonymousIdNumber(String idNumber) {
        if (StringUtils.hasLength(idNumber)) {
            int len = idNumber.length();
            String replace = "";
            for (int i = 0; i < len; i++) {
                if ((i > 5 && i < 10) || (i > len - 5)) {
                    replace += "*";
                } else {
                    replace += idNumber.charAt(i);
                }
            }
            return replace;
        }
        return idNumber;
    }


    /**
     * 获取用户手机号码的隐藏字符串
     *
     * @param phoneNumber
     *            用户手机号码
     * @return
     */
    public static String getAnonymousPhoneNumber(String phoneNumber) {
        if (StringUtils.hasLength(phoneNumber)) {
            int len = phoneNumber.length();
            String replace = "";
            for (int i = 0; i < len; i++) {
                if (i > 2 && i < 7) {
                    replace += "*";
                } else {
                    replace += phoneNumber.charAt(i);
                }
            }
            return replace;
        }
        return phoneNumber;
    }

    /**
     * 获取用户住址的隐藏字符串
     *
     * @param currentAddress
     *            用户住址
     * @return
     */
    public static String getAnonymousCurrentAddress(String currentAddress) {
        if (StringUtils.hasLength(currentAddress)
                && currentAddress.length() > 4) {
            String last = currentAddress.substring(currentAddress.length() - 4,
                    currentAddress.length());
            String stars = "";
            for (int i = 0; i < currentAddress.length() - 4; i++) {
                stars += "*";
            }
            return stars + last;
        }
        return currentAddress;
    }


}
