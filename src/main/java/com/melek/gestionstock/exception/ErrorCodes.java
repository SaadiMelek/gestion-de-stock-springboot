package com.melek.gestionstock.exception;

public enum ErrorCodes {
    ARTICLE_NOT_FOUND(1000),
    ARTICLE_NOT_VALID(1001),
    // TODO : complete the rest of error codes
    CATEGORY_NOT_FOUND(2000),
    CLIENT_NOT_FOUND(3000),
    CLIENT_NOT_VALID(3001),


    COMMANDE_CLIENT_NOT_FOUND(4000),
    COMMANDE_FOURNISSEUR_NOT_FOUND(5000),
    ENTREPRISE_NOT_FOUND(6000),
    ENTREPRISE_NOT_VALID(6001),

    FOURNISSEUR_NOT_FOUND(7000),
    LIGNE_COMMANDE_CLIENT_NOT_FOUND(8000),
    LIGNE_COMMANDE_FOURNISSEUR_NOT_FOUND(9000),
    LIGNE_VENTE_NOT_FOUND(10000),
    MOUVEMENT_STOCK_NOT_FOUND(11000),
    UTILISATEUR_NOT_FOUND(12000),
    UTILISATEUR_NOT_VALID(12001),

    VENTE_NOT_FOUND(13000)
    ;
    private int code;
    ErrorCodes(int code) {
        this.code = code;
    }
    public int getCode() {
        return code;
    }
}
