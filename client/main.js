
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
                const response = await fetch("http://localhost:5001/rest/api/v1.0/fibonacci/" + num, {
                    method: "GET",
                    headers: {
                        "Content-Type": "application/json",
                    }
                });

                const result = await response.json();

                if (result.success == true) {
                    this.getFibonacciValues();
                } else {
                    alert(result.data);
                }

            } catch (error) {
                alert("Error: ", error);
            }

        },
        async getFibonacciValues() {

            try {
                const response = await fetch("http://localhost:5001/rest/api/v1.0/fibonacci", {
                    method: "GET",
                    headers: {
                        "Content-Type": "application/json",
                    }
                });

                const result = await response.json();

                if (result.success == true) {
                    this.data = [];
                    for (var i = 0; i < result.data.length; i++) {
                        this.data.push(result.data[i]);
                    }
                } else {
                    alert(result.data);
                }

            } catch (error) {
                alert("Error: ", error);
            }
        },
        async deleteFibonacciValue(index) {

            try {
                const response = await fetch("http://localhost:5001/rest/api/v1.0/fibonacci/"+index, {
                    method: "DELETE",
                    headers: {
                        "Content-Type": "application/json",
                    }
                });

                const result = await response.json();

                if (result.success == true) {
                    this.getFibonacciValues();
                } else {
                    alert(result.data);
                }

            } catch (error) {
                alert("Error: ", error);
            }
        }
    },
    mounted() {
        this.getFibonacciValues();
    }

})