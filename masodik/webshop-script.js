let products = assignProducts();
let basket = {};
for (var item in products) {
    basket[item] = 0;
};

function mouseEnter(item) {
    item.style.cursor = 'pointer';
    item.style.background = 'rgba(25, 100, 140, 0.6)';
}

function mouseLeave(item) {
    if (basket[$(item).find('h2').attr('id')] > 0) {
        item.style.background = 'rgba(248, 209, 32, 0.7)';
        return;
    }
    item.style.background = 'rgba(185, 185, 185, 0.3)';
}

function assignProducts() {
    let products = [];
    $.getJSON('http://localhost:8080/api/v1/products/all')
        .done(function (data) {
            $.each(data, function (key, item) {
                    products.push(item);
                    $(`#${item.productName}`).append(`${item.productName} | \$${item.grossUnitPrice}`);
                });
        });
    return products;
}

function toggleItemSelection(item) {
    let productName = $(item).find('h2').attr('id');
    if (basket[productName] > 0) {
        basket[productName] = 0;
        item.style.background = 'rgba(185, 185, 185, 0.3)';
    } else {
        console.log($(item).find('input'));
        basket[productName] = $(item).find('input').val();
        item.style.background = 'rgba(248, 209, 32, 0.7)';
    }
    console.log(basket);
}

function arrayRemove(arr, value) { 
    return arr.filter(function(ele){ 
        return ele != value; 
    });
}

async function sendOrder() {
    $.ajaxSetup({
        contentType: 'application/json; charset=utf-8'
    });

    var response =  await $.post('http://localhost:8080/api/v1/orders/add',  JSON.stringify(basket));
    
    alert(response);

    listOrders();
}

function htmlEncode(value) {
    return $('<div />').text(value).html();
}

async function listOrders() {
    $("#orders").html("");
    await $.getJSON('http://localhost:8080/api/v1/orders/all')
        .done(function (data) {
            $.each(data, function (key, item) {
                var html = "<li class=\"order-item\">Order #" +
                    htmlEncode(item.id) + " | netPrice: $" +
                    htmlEncode(item.netTotalPrice) + " | grossPrice: $" +
                    htmlEncode(item.grossTotalPrice.toFixed(1)) + `<button id=\"${item.id}\" class=\"deleteOrderBtn\" onclick=\"deleteOrder(this)\">Delete order</button><ul>`;

                    $.each(item.products, function(k, v) {
                        html += "<li> - " + k + " (" + v + ")</li>";
                    })

                    html += `</ul>` + "</li>";

                $('#orders').append(html);
            });
        });

    $('html,body').animate({scrollTop: document.body.scrollHeight},"slow");
}

async function deleteOrder(item) {
    let id = $(item).attr('id');
    let response = await $.get('http://localhost:8080/api/v1/orders/delete/' + id);
    
    listOrders();
    alert(response);
}
