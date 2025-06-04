/**
 *  Class Name : EgovFileTool.java
 *  Description : 시스템 디렉토리 정보를 확인하여 제공하는  Business class
 *  Modification Information
 *
 *     수정일         수정자                   수정내용
 *   -------    --------    ---------------------------
 *   2009.01.13    조재영          최초 생성
 *   2017.03.03    조성원 	     시큐어코딩(ES)-부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
 *   2017.03.03    조성원          시큐어코딩(ES)-Null Pointer 역참조[CWE-476]
 *   2018.03.19    신용호          createDirectories() 추가 : 여러 레벨의 디렉토리를 한번에 생성
 *
 *
 *  @author 공통 서비스 개발팀 조재영,박지욱
 *  @since 2009. 01. 13
 *  @version 1.0
 *  @see
 *
 *  Copyright (C) 2009 by MOPAS  All right reserved.
 */
package egovframework.com.utl.sim.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import egovframework.com.cmm.EgovWebUtil;
import egovframework.com.cmm.util.EgovResourceCloseHelper;
import egovframework.com.utl.fcc.service.EgovStringUtil;

/**
 * EgovFileTool 클래스를 정의한다.
 *
 * @author 김진만
 * @see
 * <pre>
 * == 개정이력(Modification Information) ==
 *
 *  수정일                수정자           수정내용
 *  ----------   --------   ---------------------------
 *  2020.12.07   신용호            KISA 보안약점 조치
 *  2022.11.11   김혜준			   시큐어코딩 처리
 *
 * </pre>
 */

public class EgovFileTool {

	// 파일구분자
	static final char FILE_SEPARATOR = File.separatorChar;

	// 최대 문자길이
	static final int MAX_STR_LEN = 1024;

	// LOGGER
	private static final Logger LOGGER = LoggerFactory.getLogger(EgovFileTool.class);

	/**
	 * <pre>
	 * Comment : 디렉토리(파일)를 삭제한다. (파일,디렉토리 구분없이 존재하는 경우 무조건 삭제한다)
	 * </pre>
	 *
	 * @param filePath 삭제하고자 하는 파일의 절대경로 + 파일명
	 * @return 성공하면 삭제된 절대경로, 아니면블랭크
	 */
	public static String deletePath(String filePath) {
		String result = "";

		File file = new File(EgovWebUtil.filePathBlackList(filePath));
		if (file.exists()) {
			result = file.getAbsolutePath();
			if (!file.delete()) {
				result = "";
			}
		}

		return result;
	}

	/**
	 * <pre>
	 * Comment : 디렉토리를 생성한다. (여러 레벨의 경로를 동시에 생성)
	 * </pre>
	 *
	 * @param dirPath 생성하고자 하는 절대경로
	 * @return 성공하면 생성된 절대경로, 아니면 블랭크
	 */
	public static String createDirectories(String dirPath) {
		String result = "";

		File file = new File(EgovWebUtil.filePathBlackList(dirPath));
		if (!file.exists()) {
			if (file.mkdirs()) {
				LOGGER.debug("[file.mkdirs] file : Path Creation Success");
			} else {
				LOGGER.error("[file.mkdirs] file : Path Creation Fail");
			}
			file.getAbsolutePath();
		}

		return result;
	}

	/**
	 * 디렉토리 내부 하위목록들 중에서 파일을 찾는 기능(모든 목록 조회)
	 *
	 * @param fileArray fileArray 파일목록
	 * @return ArrayList list 파일목록(절대경로)
	 */
	public static List<String> getSubFilesByAll(File[] fileArray) throws Exception {
		ArrayList<String> list = new ArrayList<String>();

		for (int i = 0; i < fileArray.length; i++) {
			// 디렉토리 안에 디렉토리면 그 안의 파일목록에서 찾도록 재귀호출한다.
			if (fileArray[i].isDirectory()) {
				File[] tmpArray = fileArray[i].listFiles();
				list.addAll(getSubFilesByAll(tmpArray));
				// 파일이면 담는다.
			} else {
				list.add(fileArray[i].getAbsolutePath());
			}
		}

		return list;
	}

	/**
	 * <pre>
	 * Comment : 디렉토리를 생성한다.
	 * </pre>
	 *
	 * @param dirPath 생성하고자 하는 절대경로
	 * @return 성공하면 새성된 절대경로, 아니면 블랭크
	 */
	public static String createNewDirectory(String dirPath) {

		// 인자값 유효하지 않은 경우 블랭크 리턴
		if (dirPath == null || dirPath.equals("")) {
			return "";
		}

		File file = new File(EgovWebUtil.filePathBlackList(dirPath));
		String result = "";
		// 없으면 생성
		if (file.exists()) {
			// 혹시 존재해도 파일이면 생성 - 생성되지 않는다.(아래는 실질적으로는 진행되지 않음)
			if (file.isFile()) {
				//new File(file.getParent()).mkdirs();
				if (file.mkdirs()) {
					result = file.getAbsolutePath();
				}
			} else {
				result = file.getAbsolutePath();
			}
		} else {
			// 존해하지 않으면 생성
			if (file.mkdirs()) {
				result = file.getAbsolutePath();
			}
		}

		return result;
	}
	/**
	 * <pre>
	 * Comment : 파일을 생성한다.
	 * </pre>
	 *
	 * @param filePath fileName 파일의 절대경로 + 파일명
	 * @return 성공하면 생성된 파일의 절대경로, 아니면블랭크
	 */
	public static String createNewFile(String filePath) {
		// 인자값 유효하지 않은 경우 블랭크 리턴
		if (filePath == null || filePath.equals("")) {
			return "";
		}

		File file = new File(EgovWebUtil.filePathBlackList(filePath));
		String result = "";
		try {
			if (file.exists()) {
				result = filePath;
			} else {
				// 존재하지 않으면 생성함
				// 2017.02.08 이정은 시큐어코딩(ES)-부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
				if (new File(file.getParent()).mkdirs()) {
					LOGGER.debug("[file.mkdirs] file : File Creation Success");
				} else {
					LOGGER.error("[file.mkdirs] file : File Creation Fail");
				}

				if (file.createNewFile()) {
					result = file.getAbsolutePath();
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return result;
	}

	/**
	 * <pre>
	 * Comment : 파일을 삭제한다.
	 * </pre>
	 *
	 * @param fileDeletePath 삭제하고자 하는파일의 절대경로
	 * @return 성공하면 삭제된 파일의 절대경로, 아니면블랭크
	 */
	public static String deleteFile(String fileDeletePath) {
		// 인자값 유효하지 않은 경우 블랭크 리턴
		if (fileDeletePath == null || fileDeletePath.equals("")) {
			return "";
		}
		String result = "";
		File file = new File(EgovWebUtil.filePathBlackList(fileDeletePath));
		if (file.isFile()) {
			result = deletePath(fileDeletePath);
		} else {
			result = "";
		}

		return result;
	}

	/**
	 * 파일을 특정 구분자(',', '|', 'TAB')로 파싱하는 기능
	 *
	 * @param parFile 파일
	 * @param parChar 구분자(',', '|', 'TAB')
	 * @param parField 필드수
	 * @return Vector parResult 파싱결과 구조체
	 * @exception Exception
	 */
	public static Vector<List<String>> parsFileByChar(String parFile, String parChar, int parField) throws Exception {

		// 파싱결과 구조체
		Vector<List<String>> parResult = new Vector<List<String>>();

		// 파일 오픈
		String parFile1 = parFile.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
		File file = new File(EgovWebUtil.filePathBlackList(parFile1));
		BufferedReader br = null;
		try {
			// 파일이며, 존재하면 파싱 시작
			if (file.exists() && file.isFile()) {

				// 1. 파일 텍스트 내용을 읽어서 StringBuffer에 쌓는다.
				br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
				StringBuffer strBuff = new StringBuffer();
				String line = "";
				while ((line = br.readLine()) != null) {
					if (line.length() < MAX_STR_LEN) {
						strBuff.append(line);
					}
				}

				// 2. 쌓은 내용을 특정 구분자로 파싱하여 String 배열로 얻는다.
				String[] strArr = EgovStringUtil.split(strBuff.toString(), parChar);

				// 3. 필드 수 만큼 돌아가며 Vector<ArrayList> 형태로 만든다.
				int filedCnt = 1;
				List<String> arr = new ArrayList<String>();
				for (int i = 0; i < strArr.length; i++) {

					if (parField != 1) {
						if ((filedCnt % parField) == 1) {
							if (strArr[i] != null) {
								arr.add(strArr[i]);
							}
							if (i == (strArr.length - 1)) {
								parResult.add(arr);
							}
						} else if ((filedCnt % parField) == 0) {
							if (strArr[i] != null) {
								arr.add(strArr[i]);
								parResult.add(arr);
							}
						} else {
							if (strArr[i] != null) {
								arr.add(strArr[i]);
								if (i == (strArr.length - 1)) {
									parResult.add(arr);
								}
							}
						}
					} else {
						arr = new ArrayList<String>();
						if (strArr[i] != null) {
							arr.add(strArr[i]);
						}
						parResult.add(arr);
					}

					filedCnt++;
				}
			}
		} finally {
			EgovResourceCloseHelper.close(br);
		}

		return parResult;
	}
	
	/**
     * 파일 이름에서 작성자명을 추정 (예: sample_userA_202405.txt → userA)
     */
    public static String extractAuthorFromFileName(String fileName) {
        if (fileName == null || !fileName.contains("_")) return "알 수 없음";

        String[] parts = fileName.split("_");
        if (parts.length >= 2) {
            return parts[1]; // userA
        }
        return "알 수 없음";
    }
    
    /**
     * 파일 존재 여부 확인
     */
    public static boolean fileExists(String path) {
        File file = new File(path);
        return file.exists();
    }

    /**
     * 파일 크기 반환 (byte)
     */
    public static long getFileSize(String path) {
        File file = new File(path);
        return file.exists() ? file.length() : -1;
    }
    
    /**
     * 디렉토리 복사
     * @param sourcePath 복사할 원본 디렉토리 경로
     * @param targetPath 복사될 대상 디렉토리 경로
     * @return 성공 여부
     */
    public static boolean copyDirectory(String sourcePath, String targetPath) {
        File sourceDir = new File(sourcePath);
        File targetDir = new File(targetPath);

        if (!sourceDir.exists() || !sourceDir.isDirectory()) {
            return false;
        }

        if (!targetDir.exists()) {
            targetDir.mkdirs();
        }

        File[] files = sourceDir.listFiles();
        if (files == null) return false;

        for (File file : files) {
            File targetFile = new File(targetDir, file.getName());
            try {
                if (file.isDirectory()) {
                    copyDirectory(file.getAbsolutePath(), targetFile.getAbsolutePath());
                } else {
                    copyFile(file, targetFile);
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    /**
     * 파일 복사 내부 메서드
     */
    private static void copyFile(File sourceFile, File destFile) throws IOException {
        try (
            InputStream in = new FileInputStream(sourceFile);
            OutputStream out = new FileOutputStream(destFile)
        ) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
        }
    }
    
    /**
     * 파일 또는 디렉토리 삭제
     * @param path 삭제할 경로
     * @return 삭제 성공 여부
     */
    public static boolean delPath(String path) {
        File file = new File(path);

        if (!file.exists()) {
            return false;
        }

        if (file.isDirectory()) {
            return delDirectory(file);
        } else {
            return file.delete();
        }
    }

    /**
     * 디렉토리 삭제 (하위 포함)
     */
    private static boolean delDirectory(File dir) {
        File[] contents = dir.listFiles();
        if (contents != null) {
            for (File file : contents) {
                if (file.isDirectory()) {
                    delDirectory(file);
                } else {
                    file.delete();
                }
            }
        }
        return dir.delete();
    }

}
