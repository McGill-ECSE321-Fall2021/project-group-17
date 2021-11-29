import Vue from 'vue'


<template>
    <div id="holdings">
        <div class="header">
            <h3>Reservations</h3>
        </div>
        <div class="side">
            <h4 class="reservationText">Current Reservations</h4>
            <md-table md-card class="table">
                <md-table-row>
                    <md-table-cell class="th">Item Reserved</md-table-cell>
                    <md-table-cell class="th">Customer</md-table-cell>
                    <md-table-cell class="th">Last Pickup Day</md-table-cell>
                </md-table-row>
                <md-table-row v-for="reservation in reservations" :key="reservation.id" @click="selectReservation(reservation)" class="tr" md-selectable="single">
                    <md-table-cell>{{ reservation.itemInstance.checkableItem.name }}</md-table-cell>
                    <md-table-cell> {{ reservation.customer.person.name }}</md-table-cell>
                    <md-table-cell>{{ reservation.pickupDay }}</md-table-cell>
                </md-table-row>
            </md-table>
            <div class="deleteReservation">
                <md-field class="itemToDelete">
                    <label>Item</label>
                    <md-input v-model="selectedItem" disabled></md-input>
                </md-field>
            </div>
            <div class="deleteReservation">
                <md-field>
                    <label>Customer</label>
                    <md-input v-model="selectedCustomer" disabled></md-input>
                </md-field>
            </div>
            <div class="deleteReservation">
                <md-button class="md-raised" @click="deleteReservation()">Delete Reservation</md-button>
            </div>
        </div>
        <div class="side">
            <div class="createLoan">
                <h4 class="loanText">Loan Item Reserved</h4>
                <md-card class="md-primary">
                    <div class="loan">
                        <md-field class="input">
                            <label>Item</label>
                            <md-input v-model="selectedItem" placeholder="Item" disabled></md-input>
                        </md-field>
                        <md-field class="input">
                            <label>Customer</label>
                            <md-input v-model="selectedCustomer" placeholder="Customer" disabled></md-input>
                        </md-field>
                        <md-field class="input">
                            <md-datepicker v-model="returnDate"/>
                        </md-field>
                    </div>
                </md-card>
                <div class="moveButton">
                    <md-button class="md-raised" @click="createLoan()">Loan Item</md-button>
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
        right: 50%;
        width: 120%;
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

    .reservationText {
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

    .deleteReservation {
        position: relative;
        right: 30%;
        display: inline-block;
    }

    .moveButton {
        position: relative;
        left: 70%;
    }

    .itemToDelete {
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

<script src="./holdings.js">
</script>