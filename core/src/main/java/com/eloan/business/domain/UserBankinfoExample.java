package com.eloan.business.domain;

import java.util.ArrayList;
import java.util.List;

public class UserBankinfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserBankinfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andBanknameIsNull() {
            addCriterion("bankname is null");
            return (Criteria) this;
        }

        public Criteria andBanknameIsNotNull() {
            addCriterion("bankname is not null");
            return (Criteria) this;
        }

        public Criteria andBanknameEqualTo(String value) {
            addCriterion("bankname =", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameNotEqualTo(String value) {
            addCriterion("bankname <>", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameGreaterThan(String value) {
            addCriterion("bankname >", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameGreaterThanOrEqualTo(String value) {
            addCriterion("bankname >=", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameLessThan(String value) {
            addCriterion("bankname <", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameLessThanOrEqualTo(String value) {
            addCriterion("bankname <=", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameLike(String value) {
            addCriterion("bankname like", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameNotLike(String value) {
            addCriterion("bankname not like", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameIn(List<String> values) {
            addCriterion("bankname in", values, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameNotIn(List<String> values) {
            addCriterion("bankname not in", values, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameBetween(String value1, String value2) {
            addCriterion("bankname between", value1, value2, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameNotBetween(String value1, String value2) {
            addCriterion("bankname not between", value1, value2, "bankname");
            return (Criteria) this;
        }

        public Criteria andBankforknameIsNull() {
            addCriterion("bankforkname is null");
            return (Criteria) this;
        }

        public Criteria andBankforknameIsNotNull() {
            addCriterion("bankforkname is not null");
            return (Criteria) this;
        }

        public Criteria andBankforknameEqualTo(String value) {
            addCriterion("bankforkname =", value, "bankforkname");
            return (Criteria) this;
        }

        public Criteria andBankforknameNotEqualTo(String value) {
            addCriterion("bankforkname <>", value, "bankforkname");
            return (Criteria) this;
        }

        public Criteria andBankforknameGreaterThan(String value) {
            addCriterion("bankforkname >", value, "bankforkname");
            return (Criteria) this;
        }

        public Criteria andBankforknameGreaterThanOrEqualTo(String value) {
            addCriterion("bankforkname >=", value, "bankforkname");
            return (Criteria) this;
        }

        public Criteria andBankforknameLessThan(String value) {
            addCriterion("bankforkname <", value, "bankforkname");
            return (Criteria) this;
        }

        public Criteria andBankforknameLessThanOrEqualTo(String value) {
            addCriterion("bankforkname <=", value, "bankforkname");
            return (Criteria) this;
        }

        public Criteria andBankforknameLike(String value) {
            addCriterion("bankforkname like", value, "bankforkname");
            return (Criteria) this;
        }

        public Criteria andBankforknameNotLike(String value) {
            addCriterion("bankforkname not like", value, "bankforkname");
            return (Criteria) this;
        }

        public Criteria andBankforknameIn(List<String> values) {
            addCriterion("bankforkname in", values, "bankforkname");
            return (Criteria) this;
        }

        public Criteria andBankforknameNotIn(List<String> values) {
            addCriterion("bankforkname not in", values, "bankforkname");
            return (Criteria) this;
        }

        public Criteria andBankforknameBetween(String value1, String value2) {
            addCriterion("bankforkname between", value1, value2, "bankforkname");
            return (Criteria) this;
        }

        public Criteria andBankforknameNotBetween(String value1, String value2) {
            addCriterion("bankforkname not between", value1, value2, "bankforkname");
            return (Criteria) this;
        }

        public Criteria andAccountnameIsNull() {
            addCriterion("accountName is null");
            return (Criteria) this;
        }

        public Criteria andAccountnameIsNotNull() {
            addCriterion("accountName is not null");
            return (Criteria) this;
        }

        public Criteria andAccountnameEqualTo(String value) {
            addCriterion("accountName =", value, "accountname");
            return (Criteria) this;
        }

        public Criteria andAccountnameNotEqualTo(String value) {
            addCriterion("accountName <>", value, "accountname");
            return (Criteria) this;
        }

        public Criteria andAccountnameGreaterThan(String value) {
            addCriterion("accountName >", value, "accountname");
            return (Criteria) this;
        }

        public Criteria andAccountnameGreaterThanOrEqualTo(String value) {
            addCriterion("accountName >=", value, "accountname");
            return (Criteria) this;
        }

        public Criteria andAccountnameLessThan(String value) {
            addCriterion("accountName <", value, "accountname");
            return (Criteria) this;
        }

        public Criteria andAccountnameLessThanOrEqualTo(String value) {
            addCriterion("accountName <=", value, "accountname");
            return (Criteria) this;
        }

        public Criteria andAccountnameLike(String value) {
            addCriterion("accountName like", value, "accountname");
            return (Criteria) this;
        }

        public Criteria andAccountnameNotLike(String value) {
            addCriterion("accountName not like", value, "accountname");
            return (Criteria) this;
        }

        public Criteria andAccountnameIn(List<String> values) {
            addCriterion("accountName in", values, "accountname");
            return (Criteria) this;
        }

        public Criteria andAccountnameNotIn(List<String> values) {
            addCriterion("accountName not in", values, "accountname");
            return (Criteria) this;
        }

        public Criteria andAccountnameBetween(String value1, String value2) {
            addCriterion("accountName between", value1, value2, "accountname");
            return (Criteria) this;
        }

        public Criteria andAccountnameNotBetween(String value1, String value2) {
            addCriterion("accountName not between", value1, value2, "accountname");
            return (Criteria) this;
        }

        public Criteria andAccountnumberIsNull() {
            addCriterion("accountnumber is null");
            return (Criteria) this;
        }

        public Criteria andAccountnumberIsNotNull() {
            addCriterion("accountnumber is not null");
            return (Criteria) this;
        }

        public Criteria andAccountnumberEqualTo(String value) {
            addCriterion("accountnumber =", value, "accountnumber");
            return (Criteria) this;
        }

        public Criteria andAccountnumberNotEqualTo(String value) {
            addCriterion("accountnumber <>", value, "accountnumber");
            return (Criteria) this;
        }

        public Criteria andAccountnumberGreaterThan(String value) {
            addCriterion("accountnumber >", value, "accountnumber");
            return (Criteria) this;
        }

        public Criteria andAccountnumberGreaterThanOrEqualTo(String value) {
            addCriterion("accountnumber >=", value, "accountnumber");
            return (Criteria) this;
        }

        public Criteria andAccountnumberLessThan(String value) {
            addCriterion("accountnumber <", value, "accountnumber");
            return (Criteria) this;
        }

        public Criteria andAccountnumberLessThanOrEqualTo(String value) {
            addCriterion("accountnumber <=", value, "accountnumber");
            return (Criteria) this;
        }

        public Criteria andAccountnumberLike(String value) {
            addCriterion("accountnumber like", value, "accountnumber");
            return (Criteria) this;
        }

        public Criteria andAccountnumberNotLike(String value) {
            addCriterion("accountnumber not like", value, "accountnumber");
            return (Criteria) this;
        }

        public Criteria andAccountnumberIn(List<String> values) {
            addCriterion("accountnumber in", values, "accountnumber");
            return (Criteria) this;
        }

        public Criteria andAccountnumberNotIn(List<String> values) {
            addCriterion("accountnumber not in", values, "accountnumber");
            return (Criteria) this;
        }

        public Criteria andAccountnumberBetween(String value1, String value2) {
            addCriterion("accountnumber between", value1, value2, "accountnumber");
            return (Criteria) this;
        }

        public Criteria andAccountnumberNotBetween(String value1, String value2) {
            addCriterion("accountnumber not between", value1, value2, "accountnumber");
            return (Criteria) this;
        }

        public Criteria andLogininfoIdIsNull() {
            addCriterion("loginInfo_id is null");
            return (Criteria) this;
        }

        public Criteria andLogininfoIdIsNotNull() {
            addCriterion("loginInfo_id is not null");
            return (Criteria) this;
        }

        public Criteria andLogininfoIdEqualTo(Long value) {
            addCriterion("loginInfo_id =", value, "logininfoId");
            return (Criteria) this;
        }

        public Criteria andLogininfoIdNotEqualTo(Long value) {
            addCriterion("loginInfo_id <>", value, "logininfoId");
            return (Criteria) this;
        }

        public Criteria andLogininfoIdGreaterThan(Long value) {
            addCriterion("loginInfo_id >", value, "logininfoId");
            return (Criteria) this;
        }

        public Criteria andLogininfoIdGreaterThanOrEqualTo(Long value) {
            addCriterion("loginInfo_id >=", value, "logininfoId");
            return (Criteria) this;
        }

        public Criteria andLogininfoIdLessThan(Long value) {
            addCriterion("loginInfo_id <", value, "logininfoId");
            return (Criteria) this;
        }

        public Criteria andLogininfoIdLessThanOrEqualTo(Long value) {
            addCriterion("loginInfo_id <=", value, "logininfoId");
            return (Criteria) this;
        }

        public Criteria andLogininfoIdIn(List<Long> values) {
            addCriterion("loginInfo_id in", values, "logininfoId");
            return (Criteria) this;
        }

        public Criteria andLogininfoIdNotIn(List<Long> values) {
            addCriterion("loginInfo_id not in", values, "logininfoId");
            return (Criteria) this;
        }

        public Criteria andLogininfoIdBetween(Long value1, Long value2) {
            addCriterion("loginInfo_id between", value1, value2, "logininfoId");
            return (Criteria) this;
        }

        public Criteria andLogininfoIdNotBetween(Long value1, Long value2) {
            addCriterion("loginInfo_id not between", value1, value2, "logininfoId");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}