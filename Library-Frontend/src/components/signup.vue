import Vue from 'vue'
<template>
  <div id="app">
    <div class="signup-page">
      <div class="container">
        <div class="col-lg-4 col-md-6 col-sm-8 mx-auto">

            <md-tabs class="md-primary" md-alignment="centered">
              <md-tab id="tab-home" md-label="Create Account">
                <h1>Sign Up</h1>
                <form class="signup-info">
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
                <div class="acct">Already have an account?<router-link to="/login"> Login here</router-link></div>
              </md-tab>
              <md-tab id="tab-pages" md-label="Create Person">
                <h1>Sign Up</h1>
                <form class="signup-info">
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
                <div class="acct">Already have an account?<router-link to="/login"> Login here</router-link></div>
              </md-tab>
            </md-tabs>


          </div>
        </div>
      </div>

    </div>

</template>

<script src="./signup.js">
</script>

<style scoped>

.acct {
  line-height: 1rem;
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


.btn {
  width: 100px;
  height: 25px;
  margin-bottom: 20px;
  margin-top: 20px;
  color: #D7CEC7;
  background: #76323F;
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

