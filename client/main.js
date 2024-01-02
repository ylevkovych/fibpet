
const app = Vue.createApp({
  
  data: function() {
      return {
          data: []
      }
  },
  methods: {
    async countFibonacci() {
          var num = document.getElementById("num").value;

          try {
            const response =  await fetch("http://localhost:5001/rest/api/v1.0/fibonacci/"+num, {
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
                }
            });
    
            const result = await response.json();
            
            if (result.success == true) {
                this.data.push(
                {
                    index: num,
                    result: result.data,
                    date: new Date()
                });
            } else {
                alert(result.data);
            }
        
        } catch (error) {
            alert("Error: ", error);
        }
     }
  }
    
})