exports.handler = async (event) => {
    console.log(event);
    var rating = 3.25;
    var manufacturer = event["manufacturer"];
    switch(manufacturer) {
        case "BMW":
            rating = 4;
            break;
        case "Ferrari":
            rating = 5;
            break;
        case "Porsche":
            rating = 3.5;
            break;
    }
    const responseBody = {
        rating: rating
    }
    const response = {
        statusCode: 200,
        body: JSON.stringify(responseBody)
    };
    return responseBody;
};

