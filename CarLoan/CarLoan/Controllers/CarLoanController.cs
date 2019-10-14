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

        [HttpGet]
        public LoanQuote Get()
        {
            var quote = new LoanQuote();
            quote.payment = 349.34F;
            quote.rate = 3.99F;
            quote.term = 60;
            return quote;
        }
    }
}
