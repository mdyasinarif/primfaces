/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tranxactive.j2pay.gateways;

import com.tranxactive.j2pay.gateways.core.Gateway;
import com.tranxactive.j2pay.gateways.parameters.Currency;
import com.tranxactive.j2pay.gateways.parameters.Customer;
import com.tranxactive.j2pay.gateways.parameters.CustomerCard;
import com.tranxactive.j2pay.gateways.parameters.ParamList;
import com.tranxactive.j2pay.gateways.responses.*;
import com.tranxactive.j2pay.net.HTTPClient;
import com.tranxactive.j2pay.net.HTTPResponse;
import com.tranxactive.j2pay.net.JSONHelper;
import com.tranxactive.j2pay.net.QueryStringHelper;
import org.apache.http.entity.ContentType;
import org.json.JSONObject;

import static com.tranxactive.j2pay.gateways.util.ResponseProcessor.processFinalResponse;
import static com.tranxactive.j2pay.gateways.util.UniqueCustomerIdGenerator.getUniqueVaultId;

/**
 * @author Tkhan
 * @author ilyas
 */
public class EasypayGateway extends Gateway {

    private final String apiURL = "https://secure.easypaydirectgateway.com/api/transact.php";

    @Override
    public HTTPResponse purchase(JSONObject apiParameters, Customer customer, CustomerCard customerCard, Currency currency, float amount) {

        String customerVaultId = getUniqueVaultId();
        JSONObject requestObject = this.buildPurchaseParameters(apiParameters, customer, customerCard, currency, amount, customerVaultId);
        JSONObject responseObject;
        String requestString;
        String responseString;

        requestObject = JSONHelper.encode(requestObject);
        requestString = QueryStringHelper.toQueryString(requestObject);
        HTTPResponse httpResponse;

        PurchaseResponse successResponse = null;
        ErrorResponse errorResponse = new ErrorResponse();

        httpResponse = HTTPClient.httpPost(this.apiURL, requestString, ContentType.APPLICATION_FORM_URLENCODED);

        if (httpResponse.getStatusCode() == -1) {
            return httpResponse;
        }

        responseString = httpResponse.getContent();
        responseObject = JSONHelper.decode(QueryStringHelper.toJson(responseString));

        if (responseObject.getInt("response_code") == 100) {
            successResponse = new PurchaseResponse();
            successResponse.setMessage(responseObject.getString("responsetext"));
            successResponse.setTransactionId(responseObject.get("transactionid").toString());
            successResponse.setCardValuesFrom(customerCard);
            successResponse.setAmount(amount);
            successResponse.setCurrencyCode(currency);

            successResponse.setRebillParams(new JSONObject()
                    .put("customerVaultId", customerVaultId)
            );

            successResponse.setRefundParams(new JSONObject()
                    .put(ParamList.TRANSACTION_ID.getName(), responseObject.get("transactionid").toString())
            );

            successResponse.setVoidParams(new JSONObject()
                    .put(ParamList.TRANSACTION_ID.getName(), responseObject.get("transactionid").toString())
            );

        } else {
            errorResponse.setMessage(responseObject.getString("responsetext"));
            errorResponse.setTransactionId(responseObject.has("transactionid") ? responseObject.getString("transactionid") : null);
        }

        //final response.
        processFinalResponse(responseObject, httpResponse, successResponse, errorResponse);
        return httpResponse;

    }

    @Override
    public HTTPResponse refund(JSONObject apiParameters, JSONObject refundParameters, float amount) {

        JSONObject requestObject = this.buildRefundParameters(apiParameters, refundParameters, amount);
        JSONObject responseObject;
        String requestString;
        String responseString;

        requestObject = JSONHelper.encode(requestObject);
        requestString = QueryStringHelper.toQueryString(requestObject);

        HTTPResponse httpResponse;

        RefundResponse successResponse = null;
        ErrorResponse errorResponse = new ErrorResponse();

        httpResponse = HTTPClient.httpPost(this.apiURL, requestString, ContentType.APPLICATION_FORM_URLENCODED);

        if (httpResponse.getStatusCode() == -1) {
            return httpResponse;
        }

        responseString = httpResponse.getContent();
        responseObject = JSONHelper.decode(QueryStringHelper.toJson(responseString));

        if (responseObject.getInt("response_code") == 100) {
            successResponse = new RefundResponse();
            successResponse.setMessage(responseObject.getString("responsetext"));
            successResponse.setTransactionId(responseObject.get("transactionid").toString());
            successResponse.setAmount(amount);

            successResponse.setVoidParams(new JSONObject()
                    .put(ParamList.TRANSACTION_ID.getName(), responseObject.get("transactionid").toString())
            );
        } else {
            errorResponse.setMessage(responseObject.getString("responsetext"));
            errorResponse.setTransactionId(responseObject.has("transactionid") ? responseObject.getString("transactionid") : null);
        }

        //final response.
        processFinalResponse(responseObject, httpResponse, successResponse, errorResponse);
        return httpResponse;

    }

    @Override
    public HTTPResponse rebill(JSONObject apiParameters, JSONObject rebillParameters, float amount) {

        JSONObject requestObject = this.buildRebillParameters(apiParameters, rebillParameters, amount);
        JSONObject responseObject;
        String requestString;
        String responseString;
        requestObject = JSONHelper.encode(requestObject);
        requestString = QueryStringHelper.toQueryString(requestObject);
        HTTPResponse httpResponse;

        RebillResponse successResponse = null;
        ErrorResponse errorResponse = new ErrorResponse();

        httpResponse = HTTPClient.httpPost(this.apiURL, requestString, ContentType.APPLICATION_FORM_URLENCODED);

        if (httpResponse.getStatusCode() == -1) {
            return httpResponse;
        }

        responseString = httpResponse.getContent();
        responseObject = JSONHelper.decode(QueryStringHelper.toJson(responseString));

        if (responseObject.getInt("response_code") == 100) {
            successResponse = new RebillResponse();

            successResponse.setMessage(responseObject.getString("responsetext"));
            successResponse.setTransactionId(responseObject.get("transactionid").toString());

            successResponse.setAmount(amount);

            successResponse.setRebillParams(rebillParameters);

            successResponse.setRefundParams(new JSONObject()
                    .put(ParamList.TRANSACTION_ID.getName(), responseObject.get("transactionid").toString())
            );

            successResponse.setVoidParams(new JSONObject()
                    .put(ParamList.TRANSACTION_ID.getName(), responseObject.get("transactionid").toString())
            );

        } else {
            errorResponse.setMessage(responseObject.getString("responsetext"));
            errorResponse.setTransactionId(responseObject.has("transactionid") ? responseObject.getString("transactionid") : null);
        }

        //final response.
        processFinalResponse(responseObject, httpResponse, successResponse, errorResponse);
        return httpResponse;
    }

    @Override
    public HTTPResponse voidTransaction(JSONObject apiParameters, JSONObject voidParameters) {

        JSONObject requestObject = this.buildVoidParameters(apiParameters, voidParameters);
        JSONObject responseObject;
        String requestString;
        String responseString;

        requestObject = JSONHelper.encode(requestObject);
        requestString = QueryStringHelper.toQueryString(requestObject);
        HTTPResponse httpResponse;

        VoidResponse successResponse = null;
        ErrorResponse errorResponse = new ErrorResponse();

        httpResponse = HTTPClient.httpPost(this.apiURL, requestString, ContentType.APPLICATION_FORM_URLENCODED);

        if (httpResponse.getStatusCode() == -1) {
            return httpResponse;
        }

        responseString = httpResponse.getContent();
        responseObject = JSONHelper.decode(QueryStringHelper.toJson(responseString));

        if (responseObject.getInt("response_code") == 100) {
            successResponse = new VoidResponse();

            successResponse.setMessage(responseObject.getString("responsetext"));
            successResponse.setTransactionId(responseObject.get("transactionid").toString());

        } else {
            errorResponse.setMessage(responseObject.getString("responsetext"));
            errorResponse.setTransactionId(responseObject.has("transactionid") ? responseObject.getString("transactionid") : null);
        }

        //final response.
        processFinalResponse(responseObject, httpResponse, successResponse, errorResponse);
        return httpResponse;
    }

    @Override
    public HTTPResponse authorize(JSONObject apiParameters, Customer customer, CustomerCard customerCard, Currency currency, float amount) {

        String customerVaultId = getUniqueVaultId();
        JSONObject requestObject = this.buildAuthorizeParameters(apiParameters, customer, customerCard, currency, amount, customerVaultId);
        JSONObject responseObject;
        String requestString;
        String responseString;

        requestObject = JSONHelper.encode(requestObject);
        requestString = QueryStringHelper.toQueryString(requestObject);
        HTTPResponse httpResponse;

        AuthResponse successResponse = null;
        ErrorResponse errorResponse = new ErrorResponse();

        httpResponse = HTTPClient.httpPost(this.apiURL, requestString, ContentType.APPLICATION_FORM_URLENCODED);

        if (httpResponse.getStatusCode() == -1) {
            return httpResponse;
        }

        responseString = httpResponse.getContent();
        responseObject = JSONHelper.decode(QueryStringHelper.toJson(responseString));

        if (responseObject.getInt("response_code") == 100) {
            successResponse = new AuthResponse();
            successResponse.setMessage(responseObject.getString("responsetext"));
            successResponse.setTransactionId(responseObject.get("transactionid").toString());
            successResponse.setCardValuesFrom(customerCard);
            successResponse.setAmount(amount);
            successResponse.setCurrencyCode(currency);

            successResponse.setRebillParams(new JSONObject()
                    .put("customerVaultId", customerVaultId)
            );

            successResponse.setVoidParams(new JSONObject()
                    .put(ParamList.TRANSACTION_ID.getName(), responseObject.get("transactionid").toString())
            );

            successResponse.setCaptureParams(new JSONObject()
                    .put(ParamList.TRANSACTION_ID.getName(), responseObject.get("transactionid").toString())
            );

        } else {
            errorResponse.setMessage(responseObject.getString("responsetext"));
            errorResponse.setTransactionId(responseObject.has("transactionid") ? responseObject.getString("transactionid") : null);
        }

        //final response.
        processFinalResponse(responseObject, httpResponse, successResponse, errorResponse);
        return httpResponse;

    }

    @Override
    public HTTPResponse capture(JSONObject apiParameters, JSONObject captureParameters, float amount) {

        JSONObject requestObject = this.buildCaptureParameters(apiParameters, captureParameters, amount);
        JSONObject responseObject;
        String requestString;
        String responseString;
        requestObject = JSONHelper.encode(requestObject);
        requestString = QueryStringHelper.toQueryString(requestObject);
        HTTPResponse httpResponse;

        CaptureResponse successResponse = null;
        ErrorResponse errorResponse = new ErrorResponse();

        httpResponse = HTTPClient.httpPost(this.apiURL, requestString, ContentType.APPLICATION_FORM_URLENCODED);

        if (httpResponse.getStatusCode() == -1) {
            return httpResponse;
        }

        responseString = httpResponse.getContent();
        responseObject = JSONHelper.decode(QueryStringHelper.toJson(responseString));

        if (responseObject.getInt("response_code") == 100) {
            successResponse = new CaptureResponse();

            successResponse.setMessage(responseObject.getString("responsetext"));
            successResponse.setTransactionId(responseObject.get("transactionid").toString());
            successResponse.setAmount(amount);

            successResponse.setRefundParams(new JSONObject()
                    .put(ParamList.TRANSACTION_ID.getName(), responseObject.get("transactionid").toString())
            );

            successResponse.setVoidParams(new JSONObject()
                    .put(ParamList.TRANSACTION_ID.getName(), responseObject.get("transactionid").toString())
            );

        } else {
            errorResponse.setMessage(responseObject.getString("responsetext"));
            errorResponse.setTransactionId(responseObject.has("transactionid") ? responseObject.getString("transactionid") : null);
        }

        //final response.
        processFinalResponse(responseObject, httpResponse, successResponse, errorResponse);
        return httpResponse;
    }

    @Override
    public JSONObject getApiSampleParameters() {
        return new JSONObject()
                .put("username", "the api user name use demo for testing")
                .put("password", "the api password use password for testing");
    }

    @Override
    public JSONObject getRefundSampleParameters() {
        return new JSONObject()
                .put(ParamList.TRANSACTION_ID.getName(), "the transaction id which will be refunded");
    }

    @Override
    public JSONObject getRebillSampleParameters() {
        return new JSONObject().put("customerVaultId", "the customer vault id");
    }

    @Override
    public JSONObject getVoidSampleParameters() {
        return new JSONObject()
                .put(ParamList.TRANSACTION_ID.getName(), "the transaction id which will be void");
    }

    @Override
    public JSONObject getCaptureSampleParameters() {
        return new JSONObject()
                .put(ParamList.TRANSACTION_ID.getName(), "the transaction id which will be captured");
    }

    //private methods are starting below.
    private JSONObject buildPurchaseParameters(JSONObject apiParameters, Customer customer, CustomerCard customerCard, Currency currency, float amount, String customerVaultId) {

        JSONObject object = new JSONObject();
        object
                .put("type", "sale")
                .put("username", apiParameters.getString("username"))
                .put("password", apiParameters.getString("password"))
                .put("ccnumber", customerCard.getNumber())
                .put("ccexp", customerCard.getExpiryMonth() + customerCard.getExpiryYear().substring(2))
                .put("cvv", customerCard.getCvv())
                .put("amount", amount)
                .put("currency", currency)
                .put("first_name", customer.getFirstName())
                .put("last_name", customer.getLastName())
                .put("address1", customer.getAddress())
                .put("city", customer.getCity())
                .put("state", customer.getState())
                .put("zip", customer.getZip())
                .put("country", customer.getCountry().getCodeISO2())
                .put("phone", customer.getPhoneNumber())
                .put("email", customer.getEmail())
                .put("ipaddress", customer.getIp())
                .put("customer_vault", "add_customer")
                .put("customer_vault_id", customerVaultId);

        return object;

    }

    private JSONObject buildVoidParameters(JSONObject apiParameters, JSONObject voidParameters) {

        JSONObject object = new JSONObject();
        object
                .put("type", "void")
                .put("username", apiParameters.getString("username"))
                .put("password", apiParameters.getString("password"))
                .put("transactionid", voidParameters.getString(ParamList.TRANSACTION_ID.getName()));

        return object;
    }

    private JSONObject buildRefundParameters(JSONObject apiParameters, JSONObject voidParameters, float amount) {

        JSONObject object = new JSONObject();
        object
                .put("type", "refund")
                .put("username", apiParameters.getString("username"))
                .put("password", apiParameters.getString("password"))
                .put("transactionid", voidParameters.getString(ParamList.TRANSACTION_ID.getName()))
                .put("amount", Float.toString(amount));

        return object;
    }

    private JSONObject buildRebillParameters(JSONObject apiParameters, JSONObject rebillParameters, float amount) {

        JSONObject object = new JSONObject();
        object
                .put("username", apiParameters.getString("username"))
                .put("password", apiParameters.getString("password"))
                .put("customer_vault_id", rebillParameters.getString("customerVaultId"))
                .put("amount", Float.toString(amount));

        return object;
    }

    private JSONObject buildAuthorizeParameters(JSONObject apiParameters, Customer customer, CustomerCard customerCard, Currency currency, float amount, String customerVaultId) {

        JSONObject object = new JSONObject();
        object
                .put("type", "auth")
                .put("username", apiParameters.getString("username"))
                .put("password", apiParameters.getString("password"))
                .put("ccnumber", customerCard.getNumber())
                .put("ccexp", customerCard.getExpiryMonth() + customerCard.getExpiryYear().substring(2))
                .put("cvv", customerCard.getCvv())
                .put("amount", amount)
                .put("currency", currency)
                .put("first_name", customer.getFirstName())
                .put("last_name", customer.getLastName())
                .put("address1", customer.getAddress())
                .put("city", customer.getCity())
                .put("state", customer.getState())
                .put("zip", customer.getZip())
                .put("country", customer.getCountry().getCodeISO2())
                .put("phone", customer.getPhoneNumber())
                .put("email", customer.getEmail())
                .put("ipaddress", customer.getIp())
                .put("customer_vault", "add_customer")
                .put("customer_vault_id", customerVaultId);

        return object;

    }

    private JSONObject buildCaptureParameters(JSONObject apiParameters, JSONObject captureParameters, float amount) {

        JSONObject object = new JSONObject();
        object
                .put("type", "capture")
                .put("username", apiParameters.getString("username"))
                .put("password", apiParameters.getString("password"))
                .put("transactionid", captureParameters.getString(ParamList.TRANSACTION_ID.getName()))
                .put("amount", Float.toString(amount));

        return object;
    }
}
