import Vue from 'vue'
<template>
  <div id="app">
    <div class="signup-page">
      <div class="container">
        <div class="col-lg-4 col-md-6 col-sm-8 mx-auto">
          <div class="card signup">
          <md-tabs class="md-primary" md-alignment="centered">
              <md-tab id="tab-home" md-label="New Member">
                <h1>Sign Up</h1>
                <form class="signup-info">
                  <div class="address-label">
                    <label>Name</label>
                  </div>
                  <input v-model="userName" class="signup-input" placeholder="Enter Name" required>

                  <div class="address-label">
                    <label>Login Information</label>
                  </div>
                  <input v-model="userSignup" class="signup-input" placeholder="Enter Username" required>
                  <input v-model="passwordSignup" type="password" class="signup-input" placeholder="Enter Password" required>

                  <div class="address-label">
                    <label>Address</label>
                  </div>
                  <input v-model="streetNum" class="signup-input" placeholder="Street Number" required >
                  <input v-model="streetName" class="signup-input" placeholder="Street Name" required >

                  <input v-model="city" class="signup-input" placeholder="City" required >
                  <input v-model="country" class="signup-input" placeholder="Country" required >
                </form>
                <b-button size="sm" class="btn">Sign Up</b-button>
                <p>
                  <span v-if="error" style="color:red">Error: {{error}}</span>
                </p>
              </md-tab>
              <md-tab id="tab-pages" md-label="Create Account">
                <h1>Sign Up</h1>
                <div class="address-label">
                  <label>Already a Member?</label>
                </div>
                <div class="signup-info">
                  <input v-model="search" class="search" placeholder="Search for name here..." @input="searchOnTable"/>
                  <b-button class="btn" @click="onSearchClick()">Search</b-button>

                  <md-dialog :md-active.sync="searchDialog">
                    <md-dialog-title>Make Reservation</md-dialog-title>
                    <div id="options" class="wrapper">
                      <div id="Pickup day" class="dialog" >
                        <h3 style="flex-grow: 2;">Pickup date:</h3>
                        <md-datepicker v-model="selectedDate" md-immediately />
                        <!--<md-field>
                          <md-input v-model="selectedDate">
                            placeholder="date"
                          </md-input>
                        </md-field>-->
                      </div>

                      <div id="make reservation" class="dialog">
                        <md-button @click="createReservation()" class="create-button">Make Reservation</md-button>
                      </div>
                      <div id="error">
                        <md-dialog-alert :md-active.sync="error" :md-content="errorReservation" md-confirm-text="OK!" />
                      </div>
                    </div>
                  </md-dialog>
                </div>
                <form class="signup-info">
                  <div class="address-label">
                    <label>Login Information</label>
                  </div>
                  <input v-model="userSignup" type="email" class="signup-input" placeholder="Enter Username" required>
                  <input v-model="passwordSignup" type="password" class="signup-input" placeholder="Enter Password" required>
                  <div class="address-label">
                    <label>Address</label>
                  </div>
                  <input v-model="streetNum" class="signup-input" placeholder="Street Number" required >
                  <input v-model="streetName" class="signup-input" placeholder="Street Name" required >

                  <input v-model="city" class="signup-input" placeholder="City" required >
                  <input v-model="country" class="signup-input" placeholder="Country" required >
                </form>
                <b-button size="sm" class="btn">Sign Up</b-button>
                <p>
                  <span v-if="error" style="color:red">Error: {{error}}</span>
                </p>
                <div class="acct">Already have an account?<router-link to="/login"> Login here</router-link></div>
              </md-tab>
            </md-tabs>
        </div>
        </div>

      </div>
    </div>

  </div>

</template>

<script src="./signup.js">

</script>

<style scoped>

p {
  line-height: 1rem;
}

.acct {
  line-height: 1rem;
}

.card {
  padding: 0px;
  font-family: monospace;
  color: #76323F;
  background: #D7CEC7;
  align-items: center;
  font-size: 0.8rem;

}


.md-primary {
  padding: 20px;
  font-family: monospace;
  color: #76323F;
  background: #D7CEC7;
  font-size: 0.8rem;

}

.signup-input {
  margin-top: 15px;
  margin-left: 10px;
  height: 25px
}

.search{
  margin-right: 20px;
  height: 25px;
  margin-top: 20px;
}
.btn {
  width: 100px;
  height: 25px;
  margin-bottom: 20px;
  margin-top: 20px;
  color: #D7CEC7;
  background: #76323f;
  text-align: center;
  align-items: center;
}

.signup-page {
  align-items: center;
  display: flex;
  height: 100vh;
  background: url(https://images.unsplash.com/photo-1507842217343-583bb7270b66?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxleHBsb3JlLWZlZWR8MTB8fHxlbnwwfHx8fA%3D%3D&w=1000&q=80);
}

h1 {
  margin-bottom: 20px;
}


.address-label {
  margin-top: 30px;
  margin-bottom: -15px;
}


</style>

