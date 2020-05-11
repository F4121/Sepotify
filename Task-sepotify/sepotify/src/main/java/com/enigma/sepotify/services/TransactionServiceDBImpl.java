package com.enigma.sepotify.services;

import com.enigma.sepotify.entity.Album;
import com.enigma.sepotify.entity.Song;
import com.enigma.sepotify.entity.Transaction;
import com.enigma.sepotify.entity.Wallet;
import com.enigma.sepotify.exception.InsufficientBalanceException;
import com.enigma.sepotify.exception.ResourceNotFoundException;
import com.enigma.sepotify.exception.SongAlreadyPurchasedException;
import com.enigma.sepotify.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class TransactionServiceDBImpl implements TransactionService{

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    WalletService walletService;

    @Autowired
    SongService songService;

    @Autowired
    AlbumService albumService;


    @Override
    public void saveTransaction(Transaction transaction) {
        Wallet wallet = walletService.getWallet(transaction.getWallet().getId());
        checkWallet(wallet, transaction.getItems());
        paySong(wallet, transaction.getItems(), 0.0);
    }

    @Override
    public void saveAlbumTransaction(Transaction transaction) {
        Wallet wallet = walletService.getWallet(transaction.getWallet().getId());
        Album album = albumService.getAlbum(transaction.getAlbum());
        System.out.println(album.getSongs().size());
        checkWallet(wallet, album.getSongs());
        paySong(wallet, album.getSongs(), album.getDiscount());
    }

    @Override
    public Transaction getTransaction(String id) {
        Transaction transaction;
        if (transactionRepository.findById(id).isPresent()) transaction = transactionRepository.findById(id).get();
        else throw new ResourceNotFoundException(id, Transaction.class);
        return transaction;
    }

    @Override
    public Page<Transaction> searchTransaction(Pageable pageable, Transaction searchForm) {
        return null;
    }

    @Override
    public void deleteTransaction(String id) {
        if (transactionRepository.findById(id).isPresent()) transactionRepository.deleteById(id);
        else throw new ResourceNotFoundException(id, Transaction.class);
    }

    public void songTransaction(Wallet wallet, Song item, Double discount){
        Transaction newTransaction = new Transaction();

        newTransaction.setTrxDate(new Timestamp(new Date().getTime()));
        newTransaction.setAmount(songService.getSong(item.getId()).getPrice());
        newTransaction.setAlbumDiscount(discount);
        newTransaction.setItem(item);
        newTransaction.setWallet(wallet);

        walletService.transactionlWallet(wallet, (newTransaction.getAmount() - (newTransaction.getAmount() * discount / 100)));
        transactionRepository.save(newTransaction);
    }

    public void paySong(Wallet wallet, List<Song> songs, Double discount){
        Boolean alreadyPurchased = false;

            for (Song item :  songs) {
                if(wallet.getTransactions().isEmpty()){
                    this.songTransaction(wallet,item,discount);
                }else {
                    for (int i = 0; i < wallet.getTransactions().size() ; i++) {
                        if (wallet.getTransactions().get(i).getItem().getId().equals(item.getId())){
                            alreadyPurchased = true;
                            break;
                        }else if(wallet.getTransactions().size() == (i+1) ){
                            this.songTransaction(wallet,item,discount);
                        }
                    }
                }
            }

            if(alreadyPurchased){
                throw new SongAlreadyPurchasedException();
            }
    }

    public void checkWallet(Wallet wallet, List<Song> songs){
        Double totalPrice = 0.0;

        for (Song item : songs){
            totalPrice += songService.getSong(item.getId()).getPrice();;
        }

        if (totalPrice > wallet.getBalance()){
            throw new InsufficientBalanceException();
        }
    }
}
