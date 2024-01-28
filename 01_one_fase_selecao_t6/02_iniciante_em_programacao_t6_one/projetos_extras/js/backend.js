var data = {
    resource_id: '49fa9ca0-f609-4ae3-a6f7-b97bd0945a3a', // the resource id
    limit: 5, // get 5 results
    q: 'jones' // query for 'jones'
  };
  
  $.ajax({
    url: 'https://dadosabertos.aneel.gov.br/api/3/action/datastore_search',
    data: data,
    dataType: 'jsonp',
    success: function(data) {
      alert('Total results found: ' + data.result.total)
    }
  });

