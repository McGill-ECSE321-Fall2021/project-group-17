import Vue from 'vue'


<template>
    <div id="returns">
        <b-navbar toggleable="md" type="light" variant="light">
            <b-navbar variant="faded" type="light" left>
            <b-navbar-brand >
            <img src="https://i.ibb.co/L5shFK6/LMS-removebg-preview.png" alt="Logo" height="50" width="60">
            </b-navbar-brand>
            </b-navbar>
            <b-nav-item href="#">
            <a class="text-my-own-color ">
            Find movies, books, and movies
            </a>

            </b-nav-item>
                <b-nav-item href="#" class="unstyled">
                <a class="text-my-own-color ">
            View Active Loans
            </a>
            </b-nav-item >
        
            <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

            

            <!-- Right aligned nav items -->
        <b-navbar-nav class="ml-auto">
                <b-nav-form>
                <b-input-group prepend="What are you looking for today?">
                <b-form-input size="md" class="mr-sm-2" ></b-form-input>
                </b-input-group>
                </b-nav-form>


                <b-nav-item-dropdown right>
                <!-- Using 'button-content' slot -->
                <template #button-content>
                    <img src="https://icons-for-free.com/iconfiles/png/512/person+user+icon-1320166085409390336.png" alt="Logo" height="30" width="30">
                </template>
                <b-dropdown-item @click="profile()">Profile</b-dropdown-item>
                <b-dropdown-item >Sign Out</b-dropdown-item>
                </b-nav-item-dropdown>
                </b-navbar-nav>
            
        </b-navbar>
        <div class="header">
            <h3>Loans</h3>
        </div>
        <div class="side">
            <h4 class="returnText">Return an Item</h4>
            <md-table md-card class="table">
                <md-table-row>
                    <md-table-cell class="th">Item on Loan</md-table-cell>
                    <md-table-cell class="th">Customer</md-table-cell>
                    <md-table-cell class="th">Return Date</md-table-cell>
                </md-table-row>
                <md-table-row v-for="loan in loans" :key="loan.id" @click="selectLoan(loan)" class="tr" md-selectable="single">
                    <md-table-cell>{{ loan.itemInstance.checkableItem.name }}</md-table-cell>
                    <md-table-cell> {{ loan.customer.person.name }}</md-table-cell>
                    <md-table-cell>{{ loan.returnDate }}</md-table-cell>
                </md-table-row>
            </md-table>
            <div class="returnItem">
                <md-field class="itemToReturn">
                    <label>Item to Return</label>
                    <md-input v-model="selectedItem" disabled></md-input>
                </md-field>
            </div>
            <div class="returnItem">
                <md-field>
                    <label>Customer</label>
                    <md-input v-model="selectedCustomer" disabled></md-input>
                </md-field>
            </div>
            <div class="returnItem">
                <md-button class="md-raised" @click="returnItem()">Return Item</md-button>
            </div>
        </div>
        <div class="side">
            <div class="createLoan">
                <h4 class="loanText">Loan an item</h4>
                <md-card class="md-primary">
                    <div class="loan">
                        <md-field class="input">
                            <label>Library Card Id</label>
                            <md-input v-model="libCard" placeholder="Input Library Card Number"></md-input>
                        </md-field>
                        <md-field class="input">
                            <label>Serial Number</label>
                            <md-input v-model="serialNum" placeholder="Input Serial Number"></md-input>
                        </md-field>
                        <md-field class="input">
                            <md-datepicker v-model="returnDate" />
                        </md-field>
                    </div>
                </md-card>
                <div class="moveButton">
                    <md-button class="md-raised" @click="createLoan()">Loan item</md-button>
                </div>
            </div>
        </div>
        <p class="error">{{ error }}</p>
        <div class="nav">
            <nav class="navbar fixed-bottom navbar-light" style="background-color: #76323F;">
                <button class="left" @click="homepage()">
                <img src="https://cdn-icons-png.flaticon.com/512/25/25694.png" alt="Logo" height="30" width="30">
                </button>
            </nav>
        </div>
    </div>
</template>

<style>
    #returns {
        font-family: monospace;
    }

    .table {
        position: relative;
        right: 60%;
        width: 130%;
    }

    .createLoan {
        position: relative;
        right: 30%;
    }

    .th {
        background-color: #c09f80;
    }

    .tr {
        background-color: #d7cec7;
    }

    .tr:hover {
        background-color: #c09f80;
    }

    .side {
        display: inline-block;
        vertical-align: top;
        position: relative;
        top: 25px;
    }

    .returnText {
        position: relative;
        right: 40%;
    }

    .header {
        background-color: #76323f;
        height: 150%;
    }

    .md-primary {
        width: 250%;
        background-color: #d7cec7;
    }

    .loanText {
        position: relative;
        left: 70%;
    }

    .loan {
        width: 75%;
        margin-left: auto;
        margin-right: auto;
    }

    .returnItem {
        position: relative;
        right: 35%;
        display: inline-block;
    }

    .moveButton {
        position: relative;
        left: 70%;
    }

    .itemToReturn {
        right: 40%;
        width: 110%;
    }

    .text-my-own-color {
        font-size:18px;
        font-family:monospace;
            color: black;
    }

    .left {
        position: left;
    }

    .error {
        color: red;
        position: relative;
        top: 50px;
        font-size: 150%;
    }

    .nav {
        padding-top: 25%;
    }

</style>

<script src="./returns.js">
</script>