import Vue from 'vue'


<template>
    <div id="returns">
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
                <md-table-row v-for="loan in loans" :key="loan" @click="selectLoan()" class="tr" md-selectable="single">
                    <md-table-cell>{{ loan.itemInstance.checkableItem.name }}</md-table-cell>
                    <md-table-cell> {{ loan.customer.person.name }}</md-table-cell>
                    <md-table-cell>{{ loan.returnDate }}</md-table-cell>
                </md-table-row>
            </md-table>
        </div>
        <div class="side">
            <div class="createLoan">
                <h4 class="loanText">Loan an item</h4>
                <md-card class="card">
                    <div class="loan">
                    <md-field class="input">
                        <label>Library Card Id</label>
                        <md-input v-model="libCard"></md-input>
                    </md-field>
                    <md-field class="input">
                        <label>Serial Number</label>
                        <md-input v-model="serialNum"></md-input>
                    </md-field>
                    <md-field class="input">
                        <md-datepicker v-model="selectedDate" />
                    </md-field>
                    <md-button class="md-raised">Create Loan</md-button>
                    </div>
                </md-card>
            </div>
        </div>
    </div>
</template>

<style>
    h3 {
        margin-left: 0px;
    }

    .table {
        position: relative;
        right: 400px;
        width: 150%;
    }

    .createLoan {
        position: relative;
    }

    .th {
        background-color: #c09f80;
    }

    .tr {
        background-color: #d7cec7;
    }

    .side {
        display: inline-block;
        vertical-align: top;
        position: relative;
        top: 25px;
    }

    .returnText {
        position: relative;
        right: 250px;
    }

    .header {
        background-color: #76323f;
        height: 150%;
    }

    .card {
        width: 300%;
        background-color: #d7cec7;
    }

    .loanText {
        position: relative;
        left: 250px;
    }

    .loan {
        width: 75%;
        margin-left: auto;
        margin-right: auto;
    }

</style>

<script src="./returns.js">
</script>