exports.handler = async (event) => {
    console.log(event);
    var rating = 3.25;
    var manufacturer = event["manufacturer"];
    console.log("Rating request for manufacturer: " + manufacturer);
    switch(manufacturer) {
        case "BMW":
            rating = 4;
            break;
        case "Ferrari":
            rating = 5;
            break;
        case "Porsche":
            rating = 3.5;
            console.error("Error getting rating for manufacturer: " + manufacturer);
            process.exit(1)
            break;
        case "Lotus":
            rating = 3.2;
            break;
        case "Aston Martin":
            rating = 4.3;
            break;
        case "Ford":
            rating = 2.4;
            break;
        case "Jaguar":
            rating = 3.1;
            break;
        case "Lamborghini":
            rating = 4.5;
            break;
    }
    const responseBody = {
        rating: rating
    }
    console.log("Rating: " + rating + " for manufacturer: " + manufacturer);
    const response = {
        statusCode: 200,
        body: JSON.stringify(responseBody)
    };
    return responseBody;
};
