package com.floreantpos.config;

import org.apache.commons.lang.StringUtils;

import com.floreantpos.model.CardReader;
import com.floreantpos.model.MerchantGateway;
import com.floreantpos.util.AESencrp;

public class CardConfig {
	private static final String MERCHANT_PASS = "MerchantPass";
	private static final String MERCHANT_ACCOUNT = "MerchantAccount";
	private static final String MERCHANT_GATEWAY = "MerchantGateway";
	private static final String CARD_READER = "CARD_READER";

	public static void setCardReader(CardReader card) {
		if (card == null) {
			AppConfig.put(CARD_READER, "");
			return;
		}
		AppConfig.put(CARD_READER, card.name());
	}

	public static CardReader getCardReader() {
		return CardReader.fromString(AppConfig.getString(CARD_READER));
	}

	public static void setMerchantGateway(MerchantGateway gateway) {
		if (gateway == null) {
			AppConfig.put(MERCHANT_GATEWAY, "");
			return;
		}
		AppConfig.put(MERCHANT_GATEWAY, gateway.name());
	}

	public static MerchantGateway getMerchantGateway() {
		String gateway = AppConfig.getString(MERCHANT_GATEWAY);
		if (StringUtils.isEmpty(gateway)) {
			return null;
		}

		return MerchantGateway.valueOf(gateway);
	}

	public static void setMerchantAccount(String account) {
		AppConfig.put(MERCHANT_ACCOUNT, account);
	}

	public static String getMerchantAccount() {
		return AppConfig.getString(MERCHANT_ACCOUNT);
	}

	public static void setMerchantPass(String pass) {
		try {

			if (StringUtils.isEmpty(pass)) {
				AppConfig.put(MERCHANT_PASS, "");
				return;
			}

			AppConfig.put(MERCHANT_PASS, AESencrp.encrypt(pass));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getMerchantPass() throws Exception {
		String string = AppConfig.getString(MERCHANT_PASS);

		if (StringUtils.isNotEmpty(string)) {
			return AESencrp.decrypt(string);
		}

		return string;
	}
}