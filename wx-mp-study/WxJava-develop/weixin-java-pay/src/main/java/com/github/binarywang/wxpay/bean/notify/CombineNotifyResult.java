package com.github.binarywang.wxpay.bean.notify;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 * 微信支付通过支付通知接口将用户支付成功消息通知给商户
 * 文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter5_1_13.shtml
 * </pre>
 *
 * @author thinsstar
 */
@Data
@NoArgsConstructor
public class CombineNotifyResult implements Serializable {
  private static final long serialVersionUID = 1L;
  /**
   * 源数据
   */
  private OriginNotifyResponse rawData;
  /**
   * 解密后的数据
   */
  private DecryptNotifyResult result;

  @Data
  @NoArgsConstructor
  public static class DecryptNotifyResult implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * <pre>
     * 字段名：合单商户appid
     * 变量名：combine_appid
     * 是否必填：是
     * 类型：string[1,32]
     * 描述：
     *  合单发起方的appid。
     *  示例值：wxd678efh567hg6787
     * </pre>
     */
    @SerializedName(value = "combine_appid")
    private String combineAppid;
    /**
     * <pre>
     * 字段名：合单商户号
     * 变量名：combine_mchid
     * 是否必填：是
     * 类型：string[1,32]
     * 描述：
     *  合单发起方商户号。
     *  示例值：1900000109
     * </pre>
     */
    @SerializedName(value = "combine_mchid")
    private String combineMchid;
    /**
     * <pre>
     * 字段名：合单商户订单号
     * 变量名：combine_out_trade_no
     * 是否必填：是
     * 类型：string[1,32]
     * 描述：
     *  合单支付总订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
     *  示例值：P20150806125346
     * </pre>
     */
    @SerializedName(value = "combine_out_trade_no")
    private String combineOutTradeNo;
    /**
     * <pre>
     * 字段名：场景信息
     * 变量名：scene_info
     * 是否必填：否
     * 类型：object
     * 描述：
     *  支付场景信息描述
     * </pre>
     */
    @SerializedName(value = "scene_info")
    private SceneInfo sceneInfo;

    /**
     * <pre>
     * 字段名：子单信息
     * 变量名：sub_orders
     * 是否必填：是
     * 类型：array
     * 描述：
     *  最多支持子单条数：10
     * </pre>
     */
    @SerializedName(value = "sub_orders")
    private List<SubOrders> subOrders;

    /**
     * <pre>
     * 字段名：支付者
     * 变量名：combine_payer_info
     * 是否必填：否
     * 类型：object
     * 描述：
     *  示例值：见请求示例
     * </pre>
     */
    @SerializedName(value = "combine_payer_info")
    private CombinePayerInfo combinePayerInfo;
  }

  @Data
  @NoArgsConstructor
  public static class SceneInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * <pre>
     * 字段名：商户端设备号
     * 变量名：device_id
     * 是否必填：否
     * 类型：string[7,16]
     * 描述：
     *  终端设备号（门店号或收银设备ID）。
     *  特殊规则：长度最小7个字节
     *  示例值：POS1:1
     * </pre>
     */
    @SerializedName(value = "device_id")
    private String deviceId;
  }

  @Data
  @NoArgsConstructor
  public static class SubOrders implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * <pre>
     * 字段名：子单商户号
     * 变量名：mchid
     * 是否必填：是
     * 类型：string[1,32]
     * 描述：
     *  子单发起方商户号，必须与发起方Appid有绑定关系。
     *  示例值：1900000109
     * </pre>
     */
    @SerializedName(value = "mchid")
    private String mchid;
    /**
     * <pre>
     * 字段名：交易类型
     * 变量名：trade_type
     * 是否必填：是
     * 类型：string[1,16]
     * 描述：
     *  枚举值：
     *  NATIVE：扫码支付
     *  JSAPI：公众号支付
     *  APP：APP支付
     *  MWEB：H5支付
     *  示例值： JSAPI
     * </pre>
     */
    @SerializedName(value = "trade_type")
    private String tradeType;
    /**
     * <pre>
     * 字段名：交易状态
     * 变量名：trade_state
     * 是否必填：是
     * 类型：string[1,32]
     * 描述：
     *  枚举值：
     *  SUCCESS：支付成功
     *  REFUND：转入退款
     *  NOTPAY：未支付
     *  CLOSED：已关闭
     *  USERPAYING：用户支付中
     *  PAYERROR：支付失败(其他原因，如银行返回失败)
     *  示例值： SUCCESS
     * </pre>
     */
    @SerializedName(value = "trade_state")
    private String tradeState;
    /**
     * <pre>
     * 字段名：付款银行
     * 变量名：bank_type
     * 是否必填：否
     * 类型：string[1,16]
     * 描述：
     *  银行类型，采用字符串类型的银行标识。银行标识请参考《银行类型对照表》https://pay.weixin.qq.com/wiki/doc/apiv3/terms_definition/chapter1_1_3.shtml#part-6
     *  示例值：CMC
     * </pre>
     */
    @SerializedName(value = "bank_type")
    private String bankType;
    /**
     * <pre>
     * 字段名：附加信息
     * 变量名：attach
     * 是否必填：是
     * 类型：string[1,128]
     * 描述：
     *  附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用。
     *  示例值：深圳分店
     * </pre>
     */
    @SerializedName(value = "attach")
    private String attach;
    /**
     * <pre>
     * 字段名：支付完成时间
     * 变量名：success_time
     * 是否必填：是
     * 类型：string[1,32]
     * 描述：
     *  订单支付时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss:sss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss:sss表示时分秒毫秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35.120+08:00表示，北京时间2015年5月20日 13点29分35秒。
     *  示例值：2015-05-20T13:29:35.120+08:00
     * </pre>
     */
    @SerializedName(value = "success_time")
    private String successTime;
    /**
     * <pre>
     * 字段名：微信订单号
     * 变量名：transaction_id
     * 是否必填：是
     * 类型：string[1,32]
     * 描述：
     *  微信支付订单号。
     *  示例值： 1009660380201506130728806387
     * </pre>
     */
    @SerializedName(value = "transaction_id")
    private String transactionId;
    /**
     * <pre>
     * 字段名：子单商户订单号
     * 变量名：out_trade_no
     * 是否必填：是
     * 类型：string[6,32]
     * 描述：
     *  商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
     *  特殊规则：最小字符长度为6
     *  示例值：20150806125346
     * </pre>
     */
    @SerializedName(value = "out_trade_no")
    private String outTradeNo;
    /**
     * <pre>
     * 字段名：子商户应用ID
     * 变量名：sub_appid
     * 是否必填：是
     * 类型：string[1,32]
     * 描述：
     *  子商户申请的应用ID，全局唯一。请求基础下单接口时请注意APPID的应用属性，例如公众号场景下，
     *  需使用应用属性为公众号的APPID 若sub_openid有传的情况下，
     *  sub_appid必填，且sub_appid需与sub_openid对应
     *  示例值：wxd678efh567hg6999
     * </pre>
     */
    @SerializedName(value = "sub_appid")
    private String subAppid;
    /**
     * <pre>
     * 字段名：二级商户号
     * 变量名：sub_mchid
     * 是否必填：是
     * 类型：string[1,32]
     * 描述：
     *  二级商户商户号，由微信支付生成并下发。服务商子商户的商户号，被合单方。直连商户不用传二级商户号。
     *  示例值：1900000109
     * </pre>
     */
    @SerializedName(value = "sub_mchid")
    private String subMchid;
    /**
     * <pre>
     * 字段名：订单金额
     * 变量名：amount
     * 是否必填：是
     * 类型：object
     * 描述：
     *  订单金额信息
     * </pre>
     */
    @SerializedName(value = "amount")
    private Amount amount;
    /**
     * <pre>
     * 字段名：优惠功能
     * 变量名：promotion_detail
     * 是否必填：是
     * 类型：array
     * 描述：
     *  优惠功能，子单有核销优惠券时有返回
     * </pre>
     */
    @SerializedName(value = "promotion_detail")
    private List<PromotionDetail> promotionDetail;
  }

  @Data
  @NoArgsConstructor
  public static class CombinePayerInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * <pre>
     * 字段名：用户标识
     * 变量名：openid
     * 是否必填：是
     * 类型：string[1,128]
     * 描述：
     *  使用合单appid获取的对应用户openid。是用户在商户appid下的唯一标识。
     *  示例值：oUpF8uMuAJO_M2pxb1Q9zNjWeS6o
     * </pre>
     */
    @SerializedName(value = "openid")
    private String openid;
  }

  @Data
  @NoArgsConstructor
  public static class Amount implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * <pre>
     * 字段名：标价金额
     * 变量名：total_amount
     * 是否必填：是
     * 类型：int64
     * 描述：
     *  子单金额，单位为分。
     *  示例值：100
     * </pre>
     */
    @SerializedName(value = "total_amount")
    private Integer totalAmount;
    /**
     * <pre>
     * 字段名：标价币种
     * 变量名：currency
     * 是否必填：是
     * 类型：string[1,8]
     * 描述：
     *  符合ISO 4217标准的三位字母代码，人民币：CNY。
     *  示例值：CNY
     * </pre>
     */
    @SerializedName(value = "currency")
    private String currency;
    /**
     * <pre>
     * 字段名：现金支付金额
     * 变量名：payer_amount
     * 是否必填：是
     * 类型：int64
     * 描述：
     *  订单现金支付金额。
     *  示例值：10
     * </pre>
     */
    @SerializedName(value = "payer_amount")
    private Integer payerAmount;
    /**
     * <pre>
     * 字段名：现金支付币种
     * 变量名：payer_currency
     * 是否必填：是
     * 类型：string[1,8]
     * 描述：
     *  货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY。
     *  示例值： CNY
     * </pre>
     */
    @SerializedName(value = "payer_currency")
    private String payerCurrency;
  }

  @Data
  @NoArgsConstructor
  public static class PromotionDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * <pre>
     * 字段名：券ID
     * 变量名：coupon_id
     * 是否必填：是
     * 类型：string[1, 32]
     * 描述：
     *  券ID
     *  示例值：109519
     * </pre>
     */
    @SerializedName(value = "coupon_id")
    private String couponId;
    /**
     * <pre>
     * 字段名：优惠名称
     * 变量名：name
     * 是否必填：否
     * 类型：string[1, 64]
     * 描述：
     *  优惠名称
     *  示例值：单品惠-6
     * </pre>
     */
    @SerializedName(value = "name")
    private String name;
    /**
     * <pre>
     * 字段名：优惠范围
     * 变量名：scope
     * 是否必填：否
     * 类型：string[1, 32]
     * 描述：
     *  GLOBAL：全场代金券
     *  SINGLE：单品优惠
     *  示例值：GLOBALSINGLE
     * </pre>
     */
    @SerializedName(value = "scope")
    private String scope;
    /**
     * <pre>
     * 字段名：优惠类型
     * 变量名：type
     * 是否必填：否
     * 类型：string[1,8]
     * 描述：
     *  CASH：充值；
     *  NOCASH：预充值。
     *  示例值：CASH
     * </pre>
     */
    @SerializedName(value = "type")
    private String type;
    /**
     * <pre>
     * 字段名：优惠券面额
     * 变量名：amount
     * 是否必填：是
     * 类型：int
     * 描述：
     *  当前子单中享受的优惠券金额
     *  示例值：100
     * </pre>
     */
    @SerializedName(value = "amount")
    private Integer amount;
    /**
     * <pre>
     * 字段名：活动ID
     * 变量名：stock_id
     * 是否必填：否
     * 类型：string[1, 32]
     * 描述：
     *  活动ID，批次ID
     *  示例值：931386
     * </pre>
     */
    @SerializedName(value = "stock_id")
    private String stockId;
    /**
     * <pre>
     * 字段名：微信出资
     * 变量名：wechatpay_contribute
     * 是否必填：否
     * 类型：int
     * 描述：
     *  单位为分
     *  示例值：100
     * </pre>
     */
    @SerializedName(value = "wechatpay_contribute")
    private Integer wechatpayContribute;
    /**
     * <pre>
     * 字段名：商户出资
     * 变量名：merchant_contribute
     * 是否必填：否
     * 类型：int
     * 描述：
     *  单位为分
     *  示例值：100
     * </pre>
     */
    @SerializedName(value = "merchant_contribute")
    private Integer merchantContribute;
    /**
     * <pre>
     * 字段名：其他出资
     * 变量名：other_contribute
     * 是否必填：否
     * 类型：int
     * 描述：
     *  单位为分
     *  示例值：100
     * </pre>
     */
    @SerializedName(value = "other_contribute")
    private Integer otherContribute;
    /**
     * <pre>
     * 字段名：优惠币种
     * 变量名：currency
     * 是否必填：否
     * 类型：string[1,16]
     * 描述：
     *  CNY：人民币，境内商户号仅支持人民币。
     *  示例值：CNY
     * </pre>
     */
    @SerializedName(value = "currency")
    private String currency;
    /**
     * <pre>
     * 字段名：单品列表
     * 变量名：goods_detail
     * 是否必填：否
     * 类型：array
     * 描述：
     *  单品列表信息
     * </pre>
     */
    @SerializedName(value = "goods_detail")
    private List<GoodsDetail> goodsDetail;
  }

  @Data
  @NoArgsConstructor
  public static class GoodsDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * <pre>
     * 字段名：商品编码
     * 变量名：goods_id
     * 是否必填：是
     * 类型：string[1, 32]
     * 描述：
     *  商品编码
     *  示例值：M1006
     * </pre>
     */
    @SerializedName(value = "goods_id")
    private String goodsId;
    /**
     * <pre>
     * 字段名：商品数量
     * 变量名：quantity
     * 是否必填：是
     * 类型：int
     * 描述：
     *  商品数量
     *  示例值：1
     * </pre>
     */
    @SerializedName(value = "quantity")
    private Integer quantity;
    /**
     * <pre>
     * 字段名：商品单价
     * 变量名：unit_price
     * 是否必填：是
     * 类型：int
     * 描述：
     *  商品价格
     *  示例值：100
     * </pre>
     */
    @SerializedName(value = "unit_price")
    private Integer unitPrice;
    /**
     * <pre>
     * 字段名：商品优惠金额
     * 变量名：discount_amount
     * 是否必填：是
     * 类型：int
     * 描述：
     *  商品优惠金额
     *  示例值：1
     * </pre>
     */
    @SerializedName(value = "discount_amount")
    private Integer discountAmount;
    /**
     * <pre>
     * 字段名：商品备注
     * 变量名：goods_remark
     * 是否必填：否
     * 类型：string[1, 128]
     * 描述：
     *  商品备注
     *  示例值：商品备注信息
     * </pre>
     */
    @SerializedName(value = "goods_remark")
    private String goodsRemark;
  }
}
