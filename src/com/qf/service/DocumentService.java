package com.qf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qf.entity.Document;
import com.qf.mapper.IDocumentMapper;

/**
 * @ClassName: DocumentService
 * @Description: 文件的业务逻辑层
 * @author 赵凯琦
 * @date 2017-7-11 下午10:39:51
 */
@Service
public class DocumentService {
	// 注入DocumentMapper
	@Autowired
	private IDocumentMapper mapper;

	/**
	 * @Title: queryAll
	 * @Description: 获取全部通告的集合，用来查询页面的展示
	 * @param @return
	 * @return List<Document>
	 * @throws
	 */
	public List<Document> queryAll() {
		return mapper.queryAll();
	}

	/**
	 * @Title: findDocumentByTitle
	 * @Description: 通过标题查找通知
	 * @param @param docTitle
	 * @param @return
	 * @return List<Document>
	 * @throws
	 */
	public List<Document> findDocumentByTitle(String docTitle) {
		return mapper.findDocumentByTitle(docTitle);
	}


	/**
	 * @Title: deleteDocumentById
	 * @Description: 通过Id删除数据
	 * @param @param docId
	 * @return void
	 * @throws
	 */
	public void deleteDocumentById(int docId) {
		if (docId <= 0 || "".equals(docId)) {
			throw new RuntimeException("所输入数据有问题...");
		} else {
			System.out.println("业务逻辑层执行到了" + docId);
			// User user=
			mapper.deleteDocumentById(docId);
		}
	}

	/**
	 * @Title: findDocumentById
	 * @Description: 通过Id查找通知
	 * @param @param docId
	 * @param @return
	 * @return Document
	 * @throws
	 */
	public Document findDocumentById(int docId) {
		return mapper.findDocumentById(docId);
	}

	/**
	 * @Title: updateDocument
	 * @Description: 修改通知
	 * @param @param document
	 * @param @return
	 * @return boolean
	 * @throws
	 */
	public boolean updateDocument(Document document) {
		return mapper.updateDocument(document) > 0 ? true : false;
	}

	/**
	 * @Title: add
	 * @Description: 添加通知
	 * @param @param document
	 * @param @return
	 * @return boolean
	 * @throws
	 */
	public boolean add(Document document) {
		return mapper.add(document) > 0 ? true : false;
	}
}
