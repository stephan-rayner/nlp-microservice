$( document ).ready(function() {
  console.log("Frontend Javascipt is loaded.")

  $("#classify-button").click(() => {
    console.log("The button was clicked")
      $.ajax({
        url: "cheese",
        success: (result) => {
          // $("#div1").html(result);
          // console.log("CHEESE");
          console.log(result);
        }
      });
  });

});