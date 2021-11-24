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
                <md-table-row v-for="loan in loans" :key="loan" @click="selectLoan(loan)" class="tr" md-selectable="single">
                    <md-table-cell>{{ loan.itemInstance.checkableItem.name }}</md-table-cell>
                    <md-table-cell> {{ loan.customer.person.name }}</md-table-cell>
                    <md-table-cell>{{ loan.returnDate }}</md-table-cell>
                </md-table-row>
            </md-table>
            <div class="returnItem">
                <md-field>
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
                <md-button class="md-raised">Return Item</md-button>
            </div>
        </div>
        <div class="side">
            <div class="createLoan">
                <h4 class="loanText">Loan an item</h4>
                <md-card class="md-primary">
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
                            <md-datepicker v-model="checkedOut" />
                        </md-field>
                    </div>
                </md-card>
                <div class="moveButton">
                    <md-button class="md-raised">Create Loan</md-button>
                </div>
            </div>
        </div>
    </div>
</template>

<style>
    #returns {
        font-family: monospace;
    }

    .table {
        position: relative;
        right: 80%;
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
        right: 50%;
    }

    .header {
        background-color: #76323f;
        height: 150%;
    }

    .md-primary {
        width: 300%;
        background-color: #d7cec7;
    }

    .loanText {
        position: relative;
        left: 90%;
    }

    .loan {
        width: 75%;
        margin-left: auto;
        margin-right: auto;
    }

    .returnItem {
        position: relative;
        right: 50%;
        display: inline-block;
    }

    .moveButton {
        position: relative;
        left: 90%;
    }

</style>

<script src="./returns.js">
</script>