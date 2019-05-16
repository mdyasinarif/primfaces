/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tranxactive.j2pay.gateways.responses;

import com.tranxactive.j2pay.gateways.parameters.ParamList;
import org.json.JSONObject;

/**
 *
 * @author ilyas
 */
public class VoidResponse extends CoreResponse {

    public VoidResponse() {
    }

    public VoidResponse(
            String message,
            String transactionId,
            JSONObject gatewayResponse
    ) {
        this.message = message;
        this.transactionId = transactionId;
        this.gatewayResponse = gatewayResponse;
    }

    @Override
    public JSONObject getResponse() {
        return new JSONObject()
                .put(ParamList.LIBRARY_RESPONSE.getName(), new JSONObject()
                        .put(ParamList.SUCCESS.getName(), this.success)
                        .put(ParamList.MESSAGE.getName(), this.message)
                        .put(ParamList.TRANSACTION_ID.getName(), this.transactionId)
                )
                .put(ParamList.GATEWAY_RESPONSE.getName(), this.gatewayResponse != null ? this.gatewayResponse : JSONObject.NULL);
    }

}
