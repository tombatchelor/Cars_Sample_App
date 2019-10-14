using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;

namespace CarLoan.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class CarLoanController : ControllerBase
    {

        private readonly ILogger<CarLoanController> _logger;

        public CarLoanController(ILogger<CarLoanController> logger)
        {
            _logger = logger;
        }

        [HttpPost]
        public LoanQuote Post(LoanQuoteRequest quoteRequest)
        {
            var quote = new LoanQuote();
            double rate = 3.99;
            double calcRate = ((rate / 12) / 100);
            double factor = calcRate + (calcRate / (Math.Pow(calcRate + 1, quoteRequest.term) - 1));
            quote.payment = quoteRequest.loanAmount * factor;
            quote.term = quoteRequest.term;
            quote.rate = rate;
            return quote;
        }
    }
}
