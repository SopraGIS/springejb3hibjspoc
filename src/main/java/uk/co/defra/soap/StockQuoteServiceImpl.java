package uk.co.defra.soap;

import uk.co.defra.samplesoapservice.StockQuoteRequestType;
import uk.co.defra.samplesoapservice.StockQuoteResponseType;
import uk.co.defra.samplesoapservice.StockQuoteService;

public class StockQuoteServiceImpl implements StockQuoteService {

	@Override
	public StockQuoteResponseType getStockQuote(
			StockQuoteRequestType stockQuoteRequest) {
		return new StockQuoteResponseType(stockQuoteRequest.getID(), stockQuoteRequest.getCompanyCode(), Math.random());
	}

}
