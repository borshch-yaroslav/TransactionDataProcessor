package com.slavko.processor.service;

import java.io.File;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.slavko.processor.model.Transaction;
import com.slavko.processor.tools.CSVParser;
import com.slavko.processor.tools.XLSXParser;

@Service
public class TransactionService {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public List<Transaction> getAll() {
		List<Transaction> result = entityManager
				.createQuery("SELECT transaction FROM Transaction transaction", Transaction.class).getResultList();
		return result;
	}

	@Transactional
	public void add(Transaction transaction) {
		entityManager.persist(transaction);
	}

	@Transactional
	public void addTransactionsFromXLSX(File transactionsFile) {

		List<Transaction> transactions = XLSXParser.getTransactionsList(transactionsFile);

		for (Transaction transaction : transactions) {

			entityManager
					.createNativeQuery(
							"INSERT INTO transaction (account, description, currency_code, amount) VALUES(:account, :description, :currencyCode, :amount)")
					.setParameter("account", transaction.getAccount())
					.setParameter("description", transaction.getDescription())
					.setParameter("currencyCode", transaction.getCurrencyCode())
					.setParameter("amount", transaction.getAmount()).executeUpdate();
		}
	}

	@Transactional
	public void addTransactionsFromCSV(File transactionsFile) {
		List<Transaction> transactions = CSVParser.getTransactionsList(transactionsFile);

		for (Transaction transaction : transactions) {

			entityManager
					.createNativeQuery(
							"INSERT INTO transaction (account, description, currency_code, amount) VALUES(:account, :description, :currencyCode, :amount)")
					.setParameter("account", transaction.getAccount())
					.setParameter("description", transaction.getDescription())
					.setParameter("currencyCode", transaction.getCurrencyCode())
					.setParameter("amount", transaction.getAmount()).executeUpdate();
		}
	}

	@Transactional
	public void remove(Transaction transaction) {
		entityManager.createQuery("DELETE FROM Transaction transaction WHERE id=\'" + transaction.getId() + "\'")
				.executeUpdate();
	}
}