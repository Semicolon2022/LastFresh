package com.example.lastfresh.domain.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "TBL_BILLS_PRODUCT")
@Getter
@ToString
@NoArgsConstructor
public class BillProductVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BILL_PRODUCT_LIST_NUM")
    private Long billProductListNum;
    @Column(name = "BILL_PRODUCT_QUANTITY")
    private Long billProductQuantity;
    @Column(name = "BILL_PRODUCT_PRICE")
    private Long billProductPrice;
    @Column(name = "BILL_DELIVERY_METHOD")
    /*1픽업 2배달 3배송*/
    private String billDeliveryMethod;
    @Column(name = "BILL_COOKING_TIME")
    private String billCookingTime;
    @Column(name = "BILL_STATUS")
    /*(0: 대기중  1:준비중(수락 시) 2:배송중 3:배송완료 -1:취소) */
    private String billStatus;

    @ManyToOne // 다대일
    @JoinColumn(name = "SELL_PRODUCT_NUM")
    private ProductVO productVO;

    @ManyToOne // 다대일
    @JoinColumn(name = "BILL_ORDER_NUM")
    private BillVO billVO;

    @ManyToOne // 다대일
    @JoinColumn(name = "USER_NUM")
    private UserVO userVO;

    @Builder
    public BillProductVO(Long billProductListNum, Long billProductQuantity, Long billProductPrice, String billDeliveryMethod, String billCookingTime, String billStatus) {
        this.billProductListNum = billProductListNum;
        this.billProductQuantity = billProductQuantity;
        this.billProductPrice = billProductPrice;
        this.billDeliveryMethod = billDeliveryMethod;
        this.billCookingTime = billCookingTime;
        this.billStatus = billStatus;
    }
}
