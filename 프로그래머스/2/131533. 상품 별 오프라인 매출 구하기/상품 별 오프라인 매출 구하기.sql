-- 코드를 입력하세요
WITH OFFLINE_SALE_SUMS AS (
    SELECT PRODUCT_ID, SUM(SALES_AMOUNT) AS SALES_AMOUNT_SUM
    FROM OFFLINE_SALE
    GROUP BY PRODUCT_ID
)


SELECT P.PRODUCT_CODE, SUM(P.PRICE * OSS.SALES_AMOUNT_SUM) AS SALES
FROM PRODUCT P JOIN OFFLINE_SALE_SUMS OSS
    ON P.PRODUCT_ID = OSS.PRODUCT_ID
GROUP BY P.PRODUCT_CODE
ORDER BY SALES DESC, P.PRODUCT_CODE
;