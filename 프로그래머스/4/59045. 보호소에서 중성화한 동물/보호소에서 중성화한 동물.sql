-- 코드를 입력하세요
SELECT AI.ANIMAL_ID, AI.ANIMAL_TYPE, AI.NAME
FROM ANIMAL_INS AI JOIN ANIMAL_OUTS AO
    ON AI.ANIMAL_ID = AO.ANIMAL_ID
WHERE AI.SEX_UPON_INTAKE != AO.SEX_UPON_OUTCOME
ORDER BY AI.ANIMAL_ID
;