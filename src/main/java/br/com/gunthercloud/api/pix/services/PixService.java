package br.com.gunthercloud.api.pix.services;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;
import br.com.gunthercloud.api.pix.config.PixConfig;
import br.com.gunthercloud.api.pix.dto.PixRequestParamsDTO;

@Service
public class PixService {
	
	private final JSONObject configuracoes;

    public PixService(final PixConfig pixConfig){
        this.configuracoes = new JSONObject();
        this.configuracoes.put("client_id", pixConfig.clientId());
        this.configuracoes.put("client_secret", pixConfig.clientSecret());
        this.configuracoes.put("certificate", pixConfig.certificate());
        this.configuracoes.put("sandbox", pixConfig.sandbox());
        this.configuracoes.put("debug", pixConfig.debug());
    }

    public JSONObject listarChavesPix(){
        return executarOperacao("pixListEvp", new HashMap<>());
    }

    public JSONObject criarChavePix(){
        return executarOperacao("pixCreateEvp", new HashMap<>());
    }
    
    public JSONObject removerChavePix(String chave){
    	Map<String, String> map = new HashMap<>();
    	map.put("chave", chave);
        return executarOperacao("pixDeleteEvp", map);
    }
    
    public JSONObject gerarPixComQrCode(PixRequestParamsDTO body) {
    	JSONObject obj = new JSONObject();
    	obj.put("calendario", new JSONObject().put("expiracao", body.getExpiracao()));
    	obj.put("chave", body.getChave());
    	obj.put("valor", new JSONObject().put("original", body.getValor()));
    	return executarOperacao("pixCreateImmediateCharge", new HashMap<String,String>(), obj);
    }

    private JSONObject executarOperacao(String operacao, Map<String, String> params) {
        final var retorno = new JSONObject();
        try {
            EfiPay efi = new EfiPay(configuracoes);
            System.out.println(configuracoes.toString());
            JSONObject response = efi.call(operacao, params, new JSONObject());
            System.out.println(response.toString());
            return response;
        } 
        catch (EfiPayException e) {
            retorno.put("erro", e.getErrorDescription());
            e.printStackTrace();
        } 
        catch (Exception e) {
            retorno.put("erro", "Não foi possível completar a operação!");
            e.printStackTrace();
        }
        return retorno;
    }
    private JSONObject executarOperacao(String operacao, Map<String, String> params, JSONObject body) {
        final var retorno = new JSONObject();
        try {
            EfiPay efi = new EfiPay(configuracoes);
            JSONObject response = efi.call(operacao, params, body);
            return response;
        } 
        catch (EfiPayException e) {
            retorno.put("erro", e.getErrorDescription());
            e.printStackTrace();
        } 
        catch (Exception e) {
            retorno.put("erro", "Não foi possível completar a operação!");
            e.printStackTrace();
        }
        return retorno;
    }
}
